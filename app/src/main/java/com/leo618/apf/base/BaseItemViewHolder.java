package com.leo618.apf.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * function:RecyclerView的通用基类适配器需要用到的基类ViewHolder
 *
 * <p></p>
 * Created by lzj on 2017/3/10.
 */
@SuppressWarnings("ALL")
public abstract class BaseItemViewHolder extends RecyclerView.ViewHolder {
    public BaseItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
