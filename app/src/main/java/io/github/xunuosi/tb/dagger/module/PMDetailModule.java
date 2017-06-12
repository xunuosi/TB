package io.github.xunuosi.tb.dagger.module;

import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.Arrays;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.presenter.PMDetailPresenter;
import io.github.xunuosi.tb.views.view.IPMDetailActivityView;

/**
 * Created by admin on 2017/6/3.
 *
 */
@Module
public class PMDetailModule {
    private IPMDetailActivityView view;

    public PMDetailModule(IPMDetailActivityView view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    IPMDetailActivityView provideTeamManagerView() {
        return this.view;
    }

    @Provides
    @PerActivity
    PMDetailPresenter providePlayerManagerPresenter(IPMDetailActivityView view, DaoSession daoSession) {
        return new PMDetailPresenter(view, daoSession);
    }

    @Provides
    @PerActivity
    ArrayAdapter<String> provideArrayAdapter(Activity activity) {
        List<String> mList;
        String[] tNames = activity.getResources().getStringArray(R.array.team_position);
        mList = Arrays.asList(tNames);
        return new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, mList);
    }
}
