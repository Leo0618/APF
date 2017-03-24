package com.leo618.apf.ui.activity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.leo618.apf.CrashHandler;
import com.leo618.apf.R;
import com.leo618.apf.common.Const;
import com.leo618.apf.common.DeviceInfo;
import com.leo618.apf.manager.EasyActionManager;
import com.leo618.apf.manager.StatisticsManager;
import com.leo618.apf.utils.IntentLauncher;
import com.leo618.utils.FileStorageUtil;
import com.leo618.utils.PermissionUtil;
import com.leo618.utils.SPUtil;
import com.leo618.utils.UIUtil;

/**
 * function:启动页
 *
 * <p></p>
 * Created by lzj on 2016/1/28.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //6.0 or uper
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE
            };
            String[] resultPermissions = PermissionUtil.needCheckPermissions(permissions);
            if (resultPermissions.length == 0) {
                handleResultAfterInitpermissions();
            } else {
                requestPermissions(resultPermissions, 100);
            }
        } else {
            handleResultAfterInitpermissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionUtil.passPermissions(grantResults)) {
                handleResultAfterInitpermissions();
            } else {
                UIUtil.showToastShort(getString(R.string.hint_permission_splash));
                finish();
                Process.killProcess(Process.myPid());
            }
        }
    }

    private void handleResultAfterInitpermissions() {
        FileStorageUtil.initAppDir();//初始化应用文件目录
        DeviceInfo.init(getApplicationContext());// 初始化设备信息
        CrashHandler.init(getApplicationContext());// 异常捕获初始化
        UIUtil.postDelayed(mDelayEnterRunnabe, 2000);//延时进入
    }


    private Runnable mDelayEnterRunnabe = new Runnable() {
        @Override
        public void run() {
            EasyActionManager.checkIsNeedShowGuide();
            if (SPUtil.getBoolean(Const.KEY_NEED_SHOW_GUIDE, false)) {
                IntentLauncher.with(SplashActivity.this).launch(GuideActivity.class);
            } else {
                IntentLauncher.with(SplashActivity.this).launch(MainActivity.class);
            }
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        // cannot go back
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatisticsManager.onPageStart(this.getClass().getSimpleName());
        StatisticsManager.onPause(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatisticsManager.onPageEnd(this.getClass().getSimpleName());
        StatisticsManager.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UIUtil.removeCallbacksFromMainLooper(mDelayEnterRunnabe);
        mDelayEnterRunnabe = null;
    }
}
