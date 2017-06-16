package io.github.xunuosi.tb.views.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.SwipeableUltimateViewAdapter;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.model.bean.Player;

public class PMSwipeAdapter<T> extends SwipeableUltimateViewAdapter<T> {

    public PMSwipeAdapter(List<T> mData) {
        super(mData);
    }

    public void setData(List<T> list) {
        if (source == null) {
            source = list;
        } else {
            source.clear();
            source.addAll(list);
        }
        refresh();
    }

    public void refresh() {
        if (source != null && source.size() != 0) {
            notifyDataSetChanged();
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
        final SVHolder viewHolder = new SVHolder(view, true);
        return viewHolder;
    }

    @Override
    protected void withBindHolder(UltimateRecyclerviewViewHolder holder, T data, int position) {
        super.withBindHolder(holder, data, position);
        if (data instanceof Player) {
            ((SVHolder) holder).bindView((Player) data);
        }
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

    public void insertNewList(List<T> players) {
        super.insert(players);
    }


    public static class SVHolder extends UltimateRecyclerviewViewHolder {
        public static final int layout = R.layout.item_swipeable_pm;
        @BindView(R.id.iv_item_swip_pm_avator)
        ImageView mIvItemSwipPmAvator;
        @BindView(R.id.tv_item_swip_pm_name)
        TextView mTvItemSwipPmName;
        @BindView(R.id.tv_item_swip_pm_num)
        TextView mTvItemSwipPmNum;
        @BindView(R.id.tv_item_swip_pm_position)
        TextView mTvItemSwipPmPosition;
        @BindView(R.id.item_pm_swip_root)
        LinearLayout mItemPmSwipRoot;
        @BindView(R.id.recyclerview_swipe)
        SwipeLayout swipeLayout;
        @BindView(R.id.swipe_child_pm)
        LinearLayout mSwipeChildPm;

        public SVHolder(View view, boolean bind) {
            super(view);
            ButterKnife.bind(this, view);
            if (bind) {
                swipeLayout.setDrag(SwipeLayout.DragEdge.Right, mSwipeChildPm);
                swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            }
        }

        public void bindView(Player bean) {
            mTvItemSwipPmName.setText(bean.getName());
            mTvItemSwipPmNum.setText(String.valueOf(bean.getNum()));
            mTvItemSwipPmPosition.setText(bean.getPosition());
        }

        @OnClick({R.id.btn_pm_swip_edit, R.id.btn_pm_swip_delete})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.btn_pm_swip_edit:
                    Log.e("xns", "edit");
                    break;
                case R.id.btn_pm_swip_delete:
                    Log.e("xns", "delete");
                    break;
            }
        }
    }
}
