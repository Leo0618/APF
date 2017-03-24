package com.leo618.apf.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * function:ListView的通用基类适配器
 *
 * <p></p>
 * Created by lzj on 2017/3/10.
 */
@SuppressWarnings("ALL")
public abstract class BaseListViewAdapter<T> extends BaseAdapter {
    protected BaseActivity mActivity;
    protected List<T> mDataList = new ArrayList<>();

    public BaseListViewAdapter(BaseActivity activity) {
        this.mActivity = activity;
        mDataList.clear();
    }

    /**
     * 设置布局资源id
     */
    protected abstract int iSetLayoutResID();

    /**
     * 设置视图数据及其他处理
     */
    protected abstract void iBindViewData(View convertView, int position);

    /**
     * 条目点击监听
     *
     * @param view     被点击条目视图
     * @param position 点击条目位置
     */
    protected abstract void onItemClickListener(View view, int position);

    /**
     * 条目长按监听
     *
     * @param view     被长按条目视图
     * @param position 长按条目位置
     */
    protected abstract boolean onItemLongClickListener(View view, int position);

    /**
     * 加载初始
     */
    public void refreshDataList(List<T> dataList) {
        if (dataList == null || dataList.size() == 0) return;
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     */
    public void loadMoreDataList(List<T> dataList) {
        if (dataList == null || dataList.size() == 0) return;
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public T getItem(int position) {
        return (position < mDataList.size() && position >= 0) ? mDataList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 移除指定位置的条目对象
     */
    public T removeItem(int position) {
        T t = null;
        if (position < mDataList.size() && position >= 0) {
            t = mDataList.remove(position);
            notifyDataSetChanged();
        }
        return t;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(this.iSetLayoutResID(), parent, false);
        }
        if (getItem(position) == null) return convertView;
        convertView.setTag(convertView.getId(), position);
        convertView.setOnClickListener(mInnerItemOnclickListener);
        convertView.setOnLongClickListener(mInnerItemOnLongClickListener);
        this.iBindViewData(convertView, position);
        return convertView;
    }

    private View.OnClickListener mInnerItemOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (int) view.getTag(view.getId());
            if (getItem(position) == null) return;
            onItemClickListener(view, position);
        }
    };

    private View.OnLongClickListener mInnerItemOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            int position = (int) view.getTag(view.getId());
            return getItem(position) == null || onItemLongClickListener(view, position);
        }
    };

}
