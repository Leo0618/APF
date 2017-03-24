package com.leo618.apf.interf;

/**
 * function:不关心网络响应结果的地方可以使用的回调类
 *
 * <p></p>
 * Created by lzj on 2017/3/14.
 */
public class InvalidNetRespCallback extends IRequestCallback<String> {
    @Override
    public void onFailure(Exception e) {
    }

    @Override
    public void onSuccess(String s) {
    }
}
