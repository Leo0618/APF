package com.leo618.apf.interf;

import android.view.View;

/**
 * 自定义ScrollView滚动监听
 * <p></p>
 * Created by lzj on 2015/12/11.
 */
public interface OnViewScrollListener {

    void onScrollChanged(View view, int l, int t, int oldl, int oldt);
}
