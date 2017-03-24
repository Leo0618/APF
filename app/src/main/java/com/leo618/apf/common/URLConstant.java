package com.leo618.apf.common;


/**
 * function :  接口地址常量池.
 *
 * <p></p>
 * Created by lzj on 2015/12/31.
 */
public class URLConstant {
    /** Host地址 -- 安全地址 : 用于服务端安全数据交互，如Json请求获取等 */
    private static String URL_BASE_S;

    static {
        switch (Configs.STATE) {
            case 0:// 测试环境
                URL_BASE_S = "http://ppwapp-test.simuwang.com";
                break;
            case 1:// 生产环境
                URL_BASE_S = "https://ppwapp.simuwang.com";
                break;
            case 2:// 预生产环境
                URL_BASE_S = "http://ppwapp-pre.simuwang.com";
                break;
            default:
                throw new IllegalArgumentException("I don't know what the state is now. please check Configs.STATE");
        }
        android.util.Log.e("smpp", "state:" + (Configs.STATE == 0 ? "test" : Configs.STATE == 1 ? "online" : Configs.STATE == 2 ? "preOnline" : "unknow"));
    }

    //-----------------------------------------------------业务接口

    //--------------------------net interface add by lzj start
    /** app检查更新 */
    public static String APP_UPDATE_CHECK = URL_BASE_S + "/Other/getAndroidVersion?";
}