package com.leo618.apf.manager;

import android.content.Context;

import com.leo618.apf.MyApp;
import com.leo618.apf.common.Configs;
import com.leo618.apf.common.DeviceInfo;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * function:统计 管理器 (For Umeng)
 *
 * <p></p>
 * Created by lzj on 2016/6/17.
 */
@SuppressWarnings("unused")
public class StatisticsManager {
    //调试开关
    private static boolean DEBUG = Configs.DEBUG;

    //友盟统计初始化
    public static void init() {
        //是否开启调试模式
        MobclickAgent.setDebugMode(DEBUG);
        //捕获程序崩溃日志
        MobclickAgent.setCatchUncaughtExceptions(DEBUG);
        //SDK在统计Fragment时，需要关闭Activity自带的页面统计
        MobclickAgent.openActivityDurationTrack(false);
        //统计类型
        MobclickAgent.setScenarioType(MyApp.getApplication(), MobclickAgent.EScenarioType.E_UM_NORMAL);
        //日志加密设置
        MobclickAgent.enableEncrypt(true);
    }

    //构造函数
    private StatisticsManager() {
    }

    /** 页面统计 onResume */
    public static void onResume(Context context) {
        if (DEBUG) return;
        MobclickAgent.onResume(context);
    }

    /** 页面统计 onPause */
    public static void onPause(Context context) {
        if (DEBUG) return;
        MobclickAgent.onPause(context);
    }

    /** 页面统计 onPageStart */
    public static void onPageStart(String page) {
        if (DEBUG) return;
        MobclickAgent.onPageStart(page);
    }

    /** 页面统计 onPageEnd */
    public static void onPageEnd(String page) {
        if (DEBUG) return;
        MobclickAgent.onPageEnd(page);
    }

    /** 账号统计 用户登录 */
    public static void onUserLogin(String uid) {
        if (DEBUG) return;
        MobclickAgent.onProfileSignIn(uid);
        onTrackLogin(uid);
    }

    /** 账号统计 用户登出 */
    public static void onUserLogout() {
        if (DEBUG) return;
        MobclickAgent.onProfileSignOff();
    }

    /** AppTrack事件定义 注册 */
    public static void onTrackRegiste(String userId) {
        if (DEBUG) return;
        Map<String, String> regMap = new HashMap<>();
        regMap.put("userid", userId);
        MobclickAgent.onEvent(MyApp.getApplication(), "__register", regMap);
    }

    /** AppTrack事件定义 登录 */
    private static void onTrackLogin(String userId) {
        if (DEBUG) return;
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("userid", userId);
        MobclickAgent.onEvent(MyApp.getApplication(), "__login", loginMap);
    }

    /** 页面统计数据保存 onKillProcess */
    public static void onKillProcess(Context context) {
        if (DEBUG) return;
        MobclickAgent.onKillProcess(context);
    }

    /**
     * 上传计数事件到umeng管理后台(异步处理)
     *
     * @param eventId 事件ID
     */
    public static void onEvent(String eventId) {
        if (DEBUG) return;
        MobclickAgent.onEvent(MyApp.getApplication(), eventId);
    }

    /**
     * 上传参数集合事件到umeng管理后台(异步处理)
     *
     * @param eventId 事件ID
     * @param map     事件参数map
     */
    public static void onEventMap(String eventId, Map<String, String> map) {
        if (DEBUG) return;
        MobclickAgent.onEvent(MyApp.getApplication(), eventId, map);
    }

    /**
     * 获取集成测试设备识别信息
     */
    public static String getDeviceInfo(Context context) {
        return "{\"mac\":\"" + DeviceInfo.deviceMAC + "\",\"device_id\":\"" + DeviceInfo.deviceIMEI + "\"}";
    }

}




