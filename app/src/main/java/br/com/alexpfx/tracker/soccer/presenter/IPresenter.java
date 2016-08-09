package br.com.alexpfx.tracker.soccer.presenter;

/**
 * Created by alexandre on 07/08/2016.
 */
public interface IPresenter <V> {
    void attachView(V view);
    void detachView ();

}
