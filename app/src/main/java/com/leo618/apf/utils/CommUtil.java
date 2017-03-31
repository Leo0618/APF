package com.leo618.apf.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.leo618.apf.MyApp;
import com.leo618.apf.R;
import com.leo618.apf.bean.net.LoginUserInfo;
import com.leo618.apf.common.Configs;
import com.leo618.apf.common.Const;
import com.leo618.apf.event.LoginStateChangeEvent;
import com.leo618.apf.manager.EventManager;
import com.leo618.apf.manager.JsonManager;
import com.leo618.apf.manager.StatisticsManager;
import com.leo618.apf.manager.net.okhttpwrap.NetManager;
import com.leo618.apf.ui.activity.SplashActivity;
import com.leo618.utils.SPUtil;

/**
 * function:通用工具类
 * <p></p>
 * Created by lzj on 2016/1/28.
 */
@SuppressWarnings("unused")
public class CommUtil {

    /** 处理用户登录/注册成功后的通用信息 */
    public static void handleSuccessInfoAfterLogin(LoginUserInfo loginUserInfo) {
        // cache user info to local
        SPUtil.putString(Const.KEY_LOGIN_USER_INFO, JsonManager.toJSONString(loginUserInfo));
        // apply user token
        CommUtil.loginUserInfo();
        // distribute login state change event to somepage need
        EventManager.post(new LoginStateChangeEvent(true));
        // 统计账号加入到umeng后台
        if (loginUserInfo.getData() != null) {
            String userId = loginUserInfo.getData().getAccount_id();
            StatisticsManager.onUserLogin(userId);
        }
    }

    /**
     * 获取本地缓存的登录信息.
     */
    public static LoginUserInfo loginUserInfo() {
        String userInfoStr = SPUtil.getString(Const.KEY_LOGIN_USER_INFO, null);
        if (TextUtils.isEmpty(userInfoStr)) {
            return null;
        }
        LoginUserInfo loginUserInfo = JsonManager.parseObject(userInfoStr, LoginUserInfo.class);
        if (loginUserInfo == null || loginUserInfo.getToken() == null) {
            return null;
        }
        Configs.token = loginUserInfo.getToken();
        return loginUserInfo;
    }

    /**
     * 退出登录状态.
     */
    public static void logout() {
        NetManager.clearUserCookie();
        SPUtil.putString(Const.KEY_LOGIN_USER_INFO, null);
        Configs.token = null;
        EventManager.post(new LoginStateChangeEvent(false));
        StatisticsManager.onUserLogout();
    }

    /**
     * 创建桌面快捷方式
     */
    public static void addShortcut(Context context) {
        try {
            if (SPUtil.getBoolean("key_has_add_icon_on_desktop_by_auto", false)) return;
            Intent addShortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            addShortcutIntent.putExtra("duplicate", false);
            addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getString(R.string.app_name));
            addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(context, R.mipmap.ic_launcher));
            Intent launcherIntent = new Intent(Intent.ACTION_MAIN);
            launcherIntent.setClass(context, SplashActivity.class);
            launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);
            context.sendBroadcast(addShortcutIntent);
            SPUtil.putBoolean("key_has_add_icon_on_desktop_by_auto", true);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * 检测Web页面内加载url时是否是打电话交互 需要规范约束schema(tel:110)
     */
    public static boolean checkIsTelephoneInUrlAndJump(String url) {
        if (TextUtils.isEmpty(url)) return false;
        if (!url.startsWith("tel:")) return false;
        Uri uri = Uri.parse(url.replace("-", ""));
        Context context = MyApp.getApplication();
        if (context == null) return false;
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        return true;
    }


    /**
     * 跳转到应用信息页面
     */
    public static void jumpToAppInfoPage() {
        MyApp myApp = MyApp.getApplication();
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.fromParts("package", myApp.getPackageName(), null));
        myApp.startActivity(intent);
    }

    /**
     * 获取手机ABI
     */
    @SuppressWarnings("deprecation")
    public static String getABIs() {
        String abi = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String[] supportedAbis = Build.SUPPORTED_ABIS;
            for (int x = 0; x < supportedAbis.length; x++) {
                abi += supportedAbis[x] + (x == supportedAbis.length - 1 ? "" : ", ");
            }
        } else {
            abi = Build.CPU_ABI;
        }
        return abi;
    }
}
