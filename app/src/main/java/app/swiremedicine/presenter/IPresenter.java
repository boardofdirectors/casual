package app.swiremedicine.presenter;

import android.content.Context;

import app.swiremedicine.app.Myapp;
import app.swiremedicine.view.IView;

/**
 * Created by 王坤 on 2017/10/23.
 */

public abstract class IPresenter<T extends IView> {
    public T Iview;

    public IPresenter(T iview) {
        Iview = iview;
    }

    public Context getContext() {
        if (Iview != null && Iview.context() != null) {
            return Iview.context();
        }
        return Myapp.getAppcontext();
    }
}
