package com.leo618.apf.common;


/**
 * function : 常量池.
 *
 * <p>Created by lzj on 2015/12/31.</p>
 */
public final class Const {

    /** SharedPreferences文件名-->默认 */
    public static final String SP_FILENAME = "AppInfo";

    /** Intent跳转 基本数据 */
    public static final String KEY_INTENT_JUMP_DATA = "key_intent_jump_data";
    /** Intent跳转 路径传递 */
    public static final String KEY_INTENT_JUMP_PATH = "key_intent_jump_path";
    /** key ：登录用户信息 */
    public static final String KEY_LOGIN_USER_INFO = "key_login_user_info";
    /** key-->是否需要展示引导页 */
    public static final String KEY_NEED_SHOW_GUIDE = "key_need_show_guide";
    /** key-->存储上次使用的APP版本号 */
    public static final String KEY_LAST_VERSION_CODE = "key_last_version_code";
    /** key-->自选基金列表书目条数 */
    public static final String KEY_OPTION_FUND_LIST_NUMBER = "key_option_fund_list_number";
    /** key-->安装渠道 */
    public static final String KEY_INSTALL_CHANNEL = "key_channel";

    /* ****************** 视图状态常量(用于 ViewUtil工具方法) *********************/
    /** 视图状态 ： 加载中 */
    public static final int LAYOUT_LOADING = 0;
    /** 视图状态 ： 无数据空视图 */
    public static final int LAYOUT_EMPTY = LAYOUT_LOADING + 1;
    /** 视图状态 ： 出错 */
    public static final int LAYOUT_ERROR = LAYOUT_LOADING + 2;
    /** 视图状态 ： 数据视图 */
    public static final int LAYOUT_DATA = LAYOUT_LOADING + 3;

    /* ****************** 状态常量 *********************/
    /** 状态 ： 加载中 */
    public static final int STATE_LOADING = 0;
    /** 状态 ： 成功 */
    public static final int STATE_SUCCESS = STATE_LOADING + 1;
    /** 状态 ： 失败 */
    public static final int STATE_FAILED = STATE_LOADING + 2;

    /* ******************  网络请求服务端状态码常量 *********************/

    /** Reponse code : 0(ok) */
    public static final int RESP_OK = 0;
    /** Reponse code : 搜索相关接口返回成功(ok) */
    public static final int RESP_OK_SEARCH = 1;
    /** Reponse code : 4092(reLogin) */
    public static final int RESP_RE_LOGIN = 4092;

}
