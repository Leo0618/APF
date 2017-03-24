package com.leo618.apf.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.leo618.apf.MyApp;
import com.leo618.apf.manager.EventManager;
import com.leo618.apf.manager.StatisticsManager;
import com.leo618.utils.LogUtil;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.ButterKnife;


/**
 * function:android 系统中的四大组件之一Activity基类.
 * <p></p>
 * Created by lzj on 2015/12/31.
 */
@SuppressWarnings({"unused", "SameReturnValue"})
public abstract class BaseActivity extends AppCompatActivity {
    /** 日志输出标志,当前类的类名 */
    protected final String TAG = this.getClass().getSimpleName();

    /** 整个应用Applicaiton */
    private MyApp mApplication;
    /** 当前Activity的弱引用，防止内存泄露 */
    private WeakReference<Activity> activityWeakReference = null;

    /**
     * 设置activity布局视图的资源id
     */
    protected abstract int setContentViewResId();

    /**
     * 处理业务逻辑
     */
    protected abstract void doBusiness();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG, TAG + "-->onCreate()");
        int contentViewResId = this.setContentViewResId();
        if (contentViewResId == 0) {
            throw new IllegalArgumentException("must set activity's contentViewResId.");
        }
        setContentView(contentViewResId);
        mApplication = (MyApp) this.getApplication(); // 获取应用Application
        activityWeakReference = new WeakReference<Activity>(this); // 将当前Activity压入栈
        mApplication.pushTask(activityWeakReference);
        if (this.enableEventTrans()) EventManager.register(this);//事件注册
        if (this.enableButterKnife()) ButterKnife.bind(this);//ButterKnife注入绑定
        this.doBusiness();
    }

    @Override
    protected void onRestart() {
        LogUtil.d(TAG, TAG + "-->onRestart()");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        LogUtil.d(TAG, TAG + "-->onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        LogUtil.d(TAG, TAG + "-->onResume()");
        super.onResume();
        StatisticsManager.onPageStart(TAG);
        StatisticsManager.onResume(this);
    }

    @Override
    protected void onPause() {
        LogUtil.d(TAG, TAG + "-->onPause()");
        super.onPause();
        StatisticsManager.onPageEnd(TAG);
        StatisticsManager.onPause(this);
    }

    @Override
    protected void onStop() {
        LogUtil.d(TAG, TAG + "-->onStop()");
        super.onStop();
    }

    @Override
    public boolean isDestroyed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return super.isDestroyed();
        } else {
            return mDestroyed;
        }
    }

    private boolean mDestroyed;

    @Override
    protected void onDestroy() {
        LogUtil.d(TAG, TAG + "-->onDestroy()");
        if (this.enableEventTrans()) EventManager.unregister(this);
        if (this.enableButterKnife()) ButterKnife.unbind(this);//ButterKnife注入解绑
        if (mApplication != null && activityWeakReference != null) {
            mApplication.removeTask(activityWeakReference);
        }
        if (mLoading != null) {
            mLoading.dismiss();
            mLoading = null;
        }
        super.onDestroy();
        mDestroyed = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        FragmentManager supportFragmentManager = this.getSupportFragmentManager();
        List<Fragment> fragments = supportFragmentManager.getFragments();
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                fragment.onActivityResult(requestCode & 0xffff, resultCode, data);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        FragmentManager supportFragmentManager = this.getSupportFragmentManager();
        List<Fragment> fragments = supportFragmentManager.getFragments();
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /** 是否需要处理软键盘失去焦点隐藏的事件 */
    protected boolean enableDispatchTouchEventOnSoftKeyboard() {
        return true;
    }

    /** 是否禁用基类事件分发注册和反注册 */
    protected boolean enableEventTrans() {
        return true;
    }

    /** 是否禁用基类ButterKnife注册和反注册 */
    protected boolean enableButterKnife() {
        return true;
    }

    //处理失去焦点软键盘隐藏事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!this.enableDispatchTouchEventOnSoftKeyboard()) {
            return super.dispatchTouchEvent(ev);
        }
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
    }

    /**
     * 点击输入框外需要隐藏键盘
     */
    private boolean isShouldHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop); // 获取输入框当前的location位置
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            // 点击的是输入框区域，保留点击EditText的事件
            return !(ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom);
        }
        return false;
    }


    @SuppressWarnings("unchecked")
    protected final <T extends View> T findView(int id) {
        return (T) this.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    protected final <T extends View> T findView(View view, int id) {
        return (T) view.findViewById(id);
    }

    private ProgressDialog mLoading = null;

    /** 显示加载中 */
    public ProgressDialog loadingShow() {
        return loadingShow(null);
    }

    /** 显示加载中 */
    public ProgressDialog loadingShow(String msg) {
        if (this.mLoading == null) {
            mLoading = ProgressDialog.show(this, null, "加载中...");
        }
        if (!TextUtils.isEmpty(msg)) {
            this.mLoading.setMessage(msg);
        }
        return this.mLoading;
    }

    /** 隐藏加载中 */
    public void loadingDismiss() {
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }
}
