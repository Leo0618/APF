package com.leo618.apf.base;

/**
 * function: 基础响应状态类
 *
 * <p></p>
 * Created by hl on 2016/7/12.
 */
@SuppressWarnings("ALL")
public class BaseBean {
    protected int status;

    protected String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    // for showAPI retrofit,和上面二选一
    protected int showapi_res_code;
    protected String showapi_res_error;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }
}
