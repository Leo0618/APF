package com.leo618.apf.interf;


/**
 * function:操作结果回调接口
 *
 * <p></p>
 * Created by lzj on 2016/7/21.
 */
public interface IActionCallback {

    /**
     * 动作响应回调
     *
     * @param result 回调结果，true-操作成功；false-操作失败
     */
    void onCompleted(boolean result);
}
