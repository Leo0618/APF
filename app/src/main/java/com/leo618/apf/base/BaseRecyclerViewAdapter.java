package com.leo618.apf.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * function:RecyclerView的通用基类适配器
 *
 * <p></p>
 * Created by lzj on 2017/3/9.
 */
@SuppressWarnings("ALL")
public abstract class BaseRecyclerViewAdapter<T, VHH extends BaseItemViewHolder> extends RecyclerView.Adapter<VHH> {

    protected BaseActivity mActivity;
    protected List<T> mDataList = new ArrayList<>();

    public BaseRecyclerViewAdapter(BaseActivity activity) {
        this.mActivity = activity;
        mDataList.clear();
    }

    /**
     * 创建ViewHolder
     * <br/>
     * see:{@link RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)}
     */
    protected abstract VHH iCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 绑定ViewHolder视图数据
     * <br/>
     * see:{@link RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)}
     */
    protected abstract void iBindViewHolder(VHH viewHolder, int position);

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
    public int getItemCount() {
        return mDataList.size();
    }

    /**
     * 获取指定位置的条目对象
     */
    public T getItem(int position) {
        return (position < mDataList.size() && position >= 0) ? mDataList.get(position) : null;
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
    public VHH onCreateViewHolder(ViewGroup parent, int viewType) {
        return this.iCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VHH viewHolder, int position) {
        if (getItem(position) == null) return;
        viewHolder.itemView.setTag(viewHolder.itemView.getId(), position);
        viewHolder.itemView.setOnClickListener(mInnerItemOnclickListener);
        viewHolder.itemView.setOnLongClickListener(mInnerItemOnLongClickListener);
        this.iBindViewHolder(viewHolder, position);
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
