package com.leo618.apf.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leo618.apf.manager.EventManager;
import com.leo618.apf.manager.StatisticsManager;
import com.leo618.utils.LogUtil;

import butterknife.ButterKnife;


/**
 * function : Fragment基类(兼容低版本).
 * <p></p>
 * Created by lzj on 2015/12/31.
 */
@SuppressWarnings({"unused", "deprecation"})
public abstract class BaseFragment extends Fragment {
    /** 日志输出标志 **/
    protected final String TAG = this.getClass().getSimpleName();
    /** fragment根布局 */
    protected View mRootView;

    @Override
    public void onAttach(Activity activity) {
        LogUtil.d(TAG, TAG + "-->onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG, TAG + "-->onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        LogUtil.d(TAG, TAG + "-->onCreate()");
        super.onCreate(savedInstanceState);
    }

    /**
     * 设置fragment布局视图的资源id
     */
    protected abstract int setContentViewResId();

    /**
     * 处理业务逻辑
     */
    protected abstract void doBusiness();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int contentViewResId = this.setContentViewResId();
        if (contentViewResId == 0) {
            throw new IllegalArgumentException("must set activity's contentViewResId.");
        }
        if (null != mRootView) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        mRootView = inflater.inflate(contentViewResId, container, false);
        if (this.enableEventTrans()) EventManager.register(this);//事件注册
        if (this.enableButterKnife()) ButterKnife.bind(this, mRootView);//ButterKnife注入绑定
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.doBusiness();
    }

    @Override
    public void onResume() {
        LogUtil.d(TAG, TAG + "-->onResume()");
        super.onResume();
        StatisticsManager.onPageStart(TAG);
    }

    @Override
    public void onStop() {
        LogUtil.d(TAG, TAG + "-->onStop()");
        super.onStop();
    }

    @Override
    public void onPause() {
        LogUtil.d(TAG, TAG + "-->onPause()");
        super.onPause();
        StatisticsManager.onPageEnd(TAG);
    }

    @Override
    public void onDestroyView() {
        LogUtil.d(TAG, TAG + "-->onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LogUtil.d(TAG, TAG + "-->onDestroy()");
        if (this.enableEventTrans()) EventManager.unregister(this);
        if (this.enableButterKnife()) ButterKnife.unbind(this);//ButterKnife注入解绑
        if (mLoading != null) {
            mLoading.dismiss();
            mLoading = null;
        }
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        LogUtil.d(TAG, TAG + "-->onDetach()");
        super.onDetach();
    }

    @SuppressWarnings("unchecked")
    protected final <T extends View> T findView(int id) {
        return (T) mRootView.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    protected final <T extends View> T findView(View view, int id) {
        return (T) view.findViewById(id);
    }

    /** 是否禁用基类事件分发注册和反注册 */
    protected boolean enableEventTrans() {
        return true;
    }

    /** 是否禁用基类ButterKnife注册和反注册 */
    protected boolean enableButterKnife() {
        return true;
    }

    private ProgressDialog mLoading = null;

    /** 显示加载中 */
    protected ProgressDialog loadingShow() {
        return loadingShow(null);
    }

    /** 显示加载中 */
    protected ProgressDialog loadingShow(String msg) {
        if (mLoading == null) {
            mLoading = ProgressDialog.show(getActivity(), null, "加载中...");
        }
        if (!TextUtils.isEmpty(msg)) {
            mLoading.setMessage(msg);
        }
        return mLoading;
    }

    /** 隐藏加载中 */
    protected void loadingDismiss() {
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }
}
