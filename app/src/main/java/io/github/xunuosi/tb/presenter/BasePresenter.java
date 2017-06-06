package io.github.xunuosi.tb.presenter;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * Created by admin on 2017/6/6.
 *
 */

public abstract class BasePresenter<V,M> {
    protected M model;
    private WeakReference<V> view;

    public void bindView(@NonNull V view) {
        this.view = new WeakReference<V>(view);
        if (setupDone()) {
            updateView();
        }
    }

    protected abstract void updateView();

    protected V view() {
        if (view == null) {
            return null;
        } else {
            return view.get();
        }
    }

    public void unbindView() {
        this.view = null;
    }

    public void setModel(M model) {
        resetState();
        this.model = model;
        if (setupDone()) {
            updateView();
        }
    }

    protected void resetState() {
    }

    protected boolean setupDone() {
        return view() != null && model != null;
    }
}
