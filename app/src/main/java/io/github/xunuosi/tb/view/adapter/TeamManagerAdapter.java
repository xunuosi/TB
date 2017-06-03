package io.github.xunuosi.tb.view.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.model.bean.Team;

/**
 * Created by xns on 2017/6/2.
 *
 */

public class TeamManagerAdapter<T> extends UltimateViewAdapter {
    private List<T> mList;
    private LayoutInflater layoutInflater;

    @Inject
    public TeamManagerAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<T> list) {
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View layout = null;
        layout = layoutInflater.inflate(R.layout.item_team_manager, parent, false);
        holder = new TeamItemHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        T bean = mList.get(position);
        if (bean instanceof Team) {
            TeamItemHolder mHolder = (TeamItemHolder) holder;
            mHolder.tvItemTmName.setText(((Team) bean).getName());
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

    public class TeamItemHolder extends UltimateRecyclerviewViewHolder {
        @BindView(R.id.iv_item_tm_avator)
        ImageView ivItemTmAvator;
        @BindView(R.id.tv_item_tm_name)
        TextView tvItemTmName;
        @BindView(R.id.item_tm_root)
        ConstraintLayout itemTmRoot;

        TeamItemHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
