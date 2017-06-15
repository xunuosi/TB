package io.github.xunuosi.tb.views.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.model.bean.Player;

/**
 * Created by xns on 2017/6/2.
 *
 */

public class PlayerManagerAdapter<T> extends UltimateViewAdapter {
    private List<T> mList;
    private LayoutInflater layoutInflater;

    @Inject
    public PlayerManagerAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<T> list) {
        if (mList == null) {
            mList = list;
        } else {
            mList.clear();
            mList.addAll(list);
        }

    }

    public void refresh() {
        if (mList != null && mList.size() != 0) {
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View layout = null;
        layout = layoutInflater.inflate(R.layout.item_playermanager, parent, false);
        holder = new PlayerItem(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < getItemCount()
                && (customHeaderView != null ? position <= mList.size() : position < mList.size())
                && (customHeaderView != null ? position > 0 : true)) {
            T bean = mList.get(customHeaderView != null ? position - 1 : position);
            ((PlayerItem) holder).onBindView((Player) bean, true);
        }

    }

    /**
     * 插入Item的方法
     *
     * @param bean
     * @param position
     */
    public void insert(T bean, int position) {
        insertInternal(mList, bean, position);
    }

    @Override
    public RecyclerView.ViewHolder newFooterHolder(View view) {
        Log.e("xns", "newFooterHolder");
        return new UltimateRecyclerviewViewHolder<>(view);
    }

    @Override
    public RecyclerView.ViewHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public int getAdapterItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public class PlayerItem extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_pm_avator)
        ImageView mIvItemPmAvator;
        @BindView(R.id.tv_item_pm_name)
        TextView mTvItemPmName;
        @BindView(R.id.tv_item_pm_num)
        TextView mTvItemPmNum;
        @BindView(R.id.tv_item_pm_position)
        TextView mTvItemPmPosition;
        @BindView(R.id.item_pm_root)
        ConstraintLayout mItemPmRoot;

        public PlayerItem(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void onBindView(Player bean, boolean isItem) {
            if (isItem) {
                mTvItemPmName.setText(bean.getName());
                mTvItemPmNum.setText(String.valueOf(bean.getNum()));
                mTvItemPmPosition.setText(bean.getPosition());
            }
        }
    }
}
