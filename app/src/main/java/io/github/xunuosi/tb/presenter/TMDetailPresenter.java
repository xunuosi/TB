package io.github.xunuosi.tb.presenter;

import javax.inject.Inject;

import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.view.views.ITMDetailView;

/**
 * Created by admin on 2017/6/3.
 */

public class TMDetailPresenter implements ITMDetailView.Presenter {

    private ITMDetailView.View view;
    private DaoSession daoSession;

    @Inject
    public TMDetailPresenter(ITMDetailView.View view, DaoSession daoSession) {
        this.view = view;
        this.daoSession = daoSession;
    }
}
