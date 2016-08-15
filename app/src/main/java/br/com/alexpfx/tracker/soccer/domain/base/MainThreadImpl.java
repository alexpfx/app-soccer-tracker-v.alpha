package br.com.alexpfx.tracker.soccer.domain.base;


import android.os.Handler;
import android.os.Looper;

/**
 * Created by alexandre on 13/08/2016.
 */
public class MainThreadImpl implements MainThread {

    private static MainThread instace;
    private Handler handler;


    public MainThreadImpl() {
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }

    public static MainThread getInstace() {
        if (instace == null) {
            instace = new MainThreadImpl();
        }
        return instace;
    }
}
