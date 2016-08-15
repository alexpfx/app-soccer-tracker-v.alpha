package br.com.alexpfx.tracker.soccer.domain.base;

import android.content.Context;

import java.util.concurrent.Executor;

/**
 * Created by alexandre on 13/08/2016.
 */
public abstract class BaseInteractor <T extends InteractorCallback> implements Interactor <T> {
    protected Executor threadExecutor;
    protected MainThread mainThread;
    protected T callback;
    protected Context context;

    public BaseInteractor(Executor threadExecutor, MainThread mainThread, Context context) {
        this.threadExecutor = threadExecutor;
        this.mainThread = mainThread;
        this.context = context;
    }


    @Override
    public final void execute(T callback) {
        this.callback = callback;
        threadExecutor.execute(this);
    }

}
