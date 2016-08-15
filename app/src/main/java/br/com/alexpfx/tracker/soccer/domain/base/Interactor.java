package br.com.alexpfx.tracker.soccer.domain.base;

/**
 * Created by alexandre on 13/08/2016.
 */
public interface Interactor <T extends InteractorCallback> extends Runnable{
    void execute (T callback);
}
