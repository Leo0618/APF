package com.leo618.apf.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.leo618.apf.CrashHandler;
import com.leo618.apf.R;
import com.leo618.apf.common.Const;
import com.leo618.apf.common.DeviceInfo;
import com.leo618.apf.manager.EasyActionManager;
import com.leo618.apf.manager.StatisticsManager;
import com.leo618.apf.utils.IntentLauncher;
import com.leo618.mpermission.AfterPermissionGranted;
import com.leo618.mpermission.MPermission;
import com.leo618.mpermission.MPermissionSettingsDialog;
import com.leo618.utils.FileStorageUtil;
import com.leo618.utils.SPUtil;
import com.leo618.utils.UIUtil;

import java.util.List;

/**
 * function:启动页
 *
 * <p></p>
 * Created by lzj on 2016/1/28.
 */
public class SplashActivity extends AppCompatActivity implements MPermission.PermissionCallbacks {
    private static final int CODE_REQ_INIT_PERS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkInitPermissions();
    }

    /** 在启动页添加进去app必要的权限确认 */
    @AfterPermissionGranted(CODE_REQ_INIT_PERS)
    private void checkInitPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE
            };
            if (MPermission.hasPermissions(this, permissions)) {
                handleAfterPermissions();
            } else {
                MPermission.requestPermissions(this, "请授权以获取更完善的体验", CODE_REQ_INIT_PERS, permissions);
            }
        } else {
            handleAfterPermissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (MPermission.somePermissionPermanentlyDenied(this, perms)) {
            new MPermissionSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //从设置应用详情页返回
        if (requestCode == MPermissionSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            checkInitPermissions();
        }
    }

    private void handleAfterPermissions() {
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
