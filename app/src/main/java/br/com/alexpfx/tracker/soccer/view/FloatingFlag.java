package br.com.alexpfx.tracker.soccer.view;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;

import br.com.alexpfx.tracker.soccer.R;

/**
 * Created by alexandre on 09/08/2016.
 */
public class FloatingFlag extends Service {
    //solucao: adicionar imageview na view customizada.
    private WindowManager windowManager;
    private ImageView flag;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        flag = new ImageView(this);
        flag.setSelected(true);
        flag.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        flag.setScaleX(.3f);
        flag.setScaleY(.3f);

        flag.setImageResource(R.drawable.flag);

        windowManager.addView(flag, createParams());

    }

    private WindowManager.LayoutParams createParams() {
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;
        return params;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (flag != null)
            windowManager.removeView(flag);
    }
}
