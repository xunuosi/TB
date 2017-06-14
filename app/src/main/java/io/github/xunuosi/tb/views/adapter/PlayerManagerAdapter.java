package io.github.xunuosi.tb.views.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    public View getLoadMoreView() {
        return customLoadMoreView;
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
        T bean = mList.get(position);
        if (bean instanceof Player) {
            PlayerItem mHolder = (PlayerItem) holder;
            mHolder.mTvItemPmName.setText(((Player) bean).getName());
            mHolder.mTvItemPmNum.setText(String.valueOf(((Player) bean).getNum()));
            mHolder.mTvItemPmPosition.setText(((Player) bean).getPosition());
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
        return null;
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

        PlayerItem(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
