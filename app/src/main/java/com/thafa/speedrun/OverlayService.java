package com.thafa.speedrun;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class OverlayService extends Service {

private WindowManager windowManager;
private View overlayView;
private WindowManager.LayoutParams params;

private float initialTouchX;
private float initialTouchY;
private int initialX;
private int initialY;

@Override
public void onCreate() {
    super.onCreate();
    showOverlay();
}

private void showOverlay() {
    windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

    LinearLayout panel = new LinearLayout(this);
    panel.setOrientation(LinearLayout.VERTICAL);
    panel.setPadding(24, 16, 24, 16);
    panel.setBackgroundColor(Color.rgb(25, 35, 50));

    TextView title = new TextView(this);
    title.setText("THAFASPEEDRUN");
    title.setTextColor(Color.WHITE);
    title.setTextSize(14);
    title.setTypeface(null, Typeface.BOLD);
    title.setGravity(Gravity.CENTER);

    TextView status = new TextView(this);
    status.setText("Speedrun Assistant");
    status.setTextColor(Color.LTGRAY);
    status.setTextSize(12);
    status.setGravity(Gravity.CENTER);
    status.setPadding(0, 8, 0, 12);

    TextView closeButton = new TextView(this);
    closeButton.setText("TUTUP PANEL");
    closeButton.setTextColor(Color.WHITE);
    closeButton.setTextSize(12);
    closeButton.setGravity(Gravity.CENTER);
    closeButton.setPadding(12, 10, 12, 10);
    closeButton.setBackgroundColor(Color.rgb(180, 55, 55));

    panel.addView(title);
    panel.addView(status);
    panel.addView(closeButton);

    int overlayType = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

    params = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            overlayType,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
    );

    params.gravity = Gravity.TOP | Gravity.START;
    params.x = 30;
    params.y = 150;

    overlayView = panel;

    panel.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    initialX = params.x;
                    initialY = params.y;
                    initialTouchX = event.getRawX();
                    initialTouchY = event.getRawY();
                    return true;

                case MotionEvent.ACTION_MOVE:
                    params.x = initialX
                            + (int) (event.getRawX() - initialTouchX);
                    params.y = initialY
                            + (int) (event.getRawY() - initialTouchY);

                    windowManager.updateViewLayout(overlayView, params);
                    return true;
            }

            return false;
        }
    });

    closeButton.setOnClickListener(view -> stopSelf());

    try {
        windowManager.addView(overlayView, params);
    } catch (Exception e) {
        Toast.makeText(
                this,
                "Gagal menampilkan overlay.",
                Toast.LENGTH_LONG
        ).show();
        stopSelf();
    }
}

@Override
public void onDestroy() {
    super.onDestroy();

    if (windowManager != null && overlayView != null) {
        try {
            windowManager.removeView(overlayView);
        } catch (Exception ignored) {
            // View mungkin sudah dilepas oleh sistem.
        }
    }
}

@Override
public IBinder onBind(Intent intent) {
    return null;
}

}