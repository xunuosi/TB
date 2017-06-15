package io.github.xunuosi.tb.views.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.marshalchen.ultimaterecyclerview.SwipeableUltimateViewAdapter;
import com.marshalchen.ultimaterecyclerview.URLogs;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeLayout;

import java.util.List;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.model.bean.Player;

public class PMSwipeAdapter<T> extends SwipeableUltimateViewAdapter<T> {

    private List<T> mList;

    public PMSwipeAdapter(List<T> mData) {
        super(mData);
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
    protected void withBindHolder(UltimateRecyclerviewViewHolder holder, T data, int position) {
        super.withBindHolder(holder, data, position);
        if (data instanceof Player) {
            ((SVHolder) holder).mTvItemPmName.setText(((Player) data).getName());
        }
    }

    /**
     * the layout id for the normal data
     *
     * @return the ID
     */
    @Override
    protected int getNormalLayoutResId() {
        return SVHolder.layout;
    }

    /**
     * this is the Normal View Holder initiation
     *
     * @param view view
     * @return holder
     */
    @Override
    protected UltimateRecyclerviewViewHolder newViewHolder(final View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URLogs.d("click");
            }
        });
        final SVHolder viewHolder = new SVHolder(view, true);
        viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(view.getContext(), "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAt(viewHolder.getPosition());
                Toast.makeText(view.getContext(), "Deleted " + viewHolder.getPosition(), Toast.LENGTH_SHORT).show();
            }
        });

        return viewHolder;
    }

    @Override
    public SVHolder newFooterHolder(View view) {
        return new SVHolder(view, false);
    }

    @Override
    public SVHolder newHeaderHolder(View view) {
        return new SVHolder(view, false);
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    protected void removeNotifyExternal(int pos) {
        closeItem(pos);
    }


    public static class SVHolder extends UltimateRecyclerviewViewHolder {
        public static final int layout = R.layout.item_swipeable_pm;

        ImageView mIvItemPmAvator;

        TextView mTvItemPmName;

        TextView mTvItemPmNum;

        TextView mTvItemPmPosition;

        ImageView mTrash;

        Button mDelete;

        SwipeLayout mRecyclerviewSwipe;

        public SVHolder(View itemView, boolean bind) {
            super(itemView);
            mRecyclerviewSwipe = (SwipeLayout) itemView.findViewById(R.id.recyclerview_swipe);
            mDelete = (Button) itemView.findViewById(R.id.delete);
            mTvItemPmName = (TextView) itemView.findViewById(R.id.tv_item_pm_name);
            if (bind) {
                mRecyclerviewSwipe.setDragEdge(SwipeLayout.DragEdge.Right);
                mRecyclerviewSwipe.setShowMode(SwipeLayout.ShowMode.PullOut);
            }
        }

    }
}
