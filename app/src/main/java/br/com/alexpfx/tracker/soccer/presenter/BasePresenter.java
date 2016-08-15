package br.com.alexpfx.tracker.soccer.presenter;


/**
 * Created by alexandre on 07/08/2016.
 */
public abstract class BasePresenter<V> implements IPresenter<V> {
    protected V view;


    public void attachView(V view) {
        this.view = view;
    }


    public void detachView() {
        this.view = null;
    }


}
