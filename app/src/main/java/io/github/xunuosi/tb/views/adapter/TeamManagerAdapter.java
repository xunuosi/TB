package io.github.xunuosi.tb.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

import javax.inject.Inject;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.model.bean.Team;
import io.github.xunuosi.tb.views.holder.TeamItemHolder;

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
        if (mList == null) {
            mList = list;
        } else {
            mList.clear();
            mList.addAll(list);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TeamItemHolder<T>(layoutInflater.inflate(R.layout.item_team_manager, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        T bean = mList.get(position);
        if (bean instanceof Team) {
            final TeamItemHolder mHolder = (TeamItemHolder) holder;
            mHolder.bindData(bean);
            mHolder.itemTmRoot.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
    }

    public interface PopWindowListener {
        
        void showPopWindow();

        void hidePopWindow();
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

    public void refresh() {
        if (mList != null && mList.size() != 0) {
            notifyDataSetChanged();
        }
    }
}
