package com.leo618.apf.base;

/**
 * function:UI的逻辑处理基类
 * <p></p>
 * Created by lzj on 2016/5/31.
 */
@SuppressWarnings("unused")
public abstract class BaseHelper {
    /** 日志输出标志,当前类的类名 */
    protected final String TAG = this.getClass().getSimpleName();

    public abstract void onDestroy();
}
