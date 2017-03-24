package com.leo618.apf.base;

import android.content.ContentProvider;

/**
 * function: android 系统中的四大组件之一ContentProvider基类.
 * <p></p>
 * Created by lzj on 2015/12/31.
 */
public abstract class BaseContentProvider extends ContentProvider {

    /** 日志输出标志 **/
    protected final String TAG = this.getClass().getSimpleName();
}
