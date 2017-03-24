package com.leo618.apf.common;


/**
 * function : 通用配置类.
 *
 * <p>Created by lzj on 2015/12/31.<p/>
 */
public final class Configs {

    /** 当前APK模式 0-测试模式; 1-生产模式; 2-预生产模式 */
    public static int STATE = 0;

    /** 调试开关 */
    public static boolean DEBUG = true;

    static {
        //测试环境
        if (0 == STATE) {
            DEBUG = true;
        }
        //生产环境
        else if (1 == STATE) {
            DEBUG = false;
        }
        //预生产环境
        else if (2 == STATE) {
            DEBUG = false;
        }
        //未知
        else {
            throw new RuntimeException("STATE is wrong!!!");
        }
    }

    /** 日志记录目录 */
    public static final String PATH_BASE_LOG = "/.crashLog/";
    /** 临时文件目录 */
    public static final String PATH_BASE_TEMP = "/temp/";
    /** 公共图片文件目录 */
    public static final String PATH_BASE_PICTURE = "/picture/";
    /** 公共图片文件缓存目录 */
    public static final String PATH_BASE_PICTURE_CACHE = PATH_BASE_PICTURE + "/cache";

    /** 用户token */
    public static String token;
}
