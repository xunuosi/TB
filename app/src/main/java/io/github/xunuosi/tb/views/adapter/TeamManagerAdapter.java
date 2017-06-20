package io.github.xunuosi.tb.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
    private PopWindowListener listener;
    private int touchX, touchY;

    @Inject
    public TeamManagerAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void setListener(PopWindowListener listener) {
        this.listener = listener;
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final T bean = mList.get(position);
        if (bean instanceof Team) {
            final TeamItemHolder mHolder = (TeamItemHolder) holder;
            mHolder.bindData(bean);

            mHolder.itemTmRoot.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            touchX = (int) event.getX();
                            touchY = (int) event.getY();
                            break;
                    }
                    return false;
                }
            });
            mHolder.itemTmRoot.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) {
                        listener.showPopWindow(v, touchX, touchY, position);
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    public void removeItemAtIndex(int position) {
        if (mList != null) {
            mList.remove(position);
            notifyDataSetChanged();
        }
    }

    public interface PopWindowListener {

        void showPopWindow(View v, int x, int y, int position);

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
