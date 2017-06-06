package io.github.xunuosi.tb.views.holder;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.model.bean.Team;

/**
 * Created by admin on 2017/6/6.
 *
 */

public class TeamItemHolder<T> extends UltimateRecyclerviewViewHolder {
    @BindView(R.id.iv_item_tm_avator)
    ImageView ivItemTmAvator;
    @BindView(R.id.tv_item_tm_name)
    TextView tvItemTmName;
    @BindView(R.id.item_tm_root)
    ConstraintLayout itemTmRoot;

    private T data;

    public TeamItemHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bindData(T data) {
        this.data = data;
        if (data instanceof Team) {
            showTeamView();
        }
    }

    private void showTeamView() {
        tvItemTmName.setText(((Team) data).getName());
    }

}
