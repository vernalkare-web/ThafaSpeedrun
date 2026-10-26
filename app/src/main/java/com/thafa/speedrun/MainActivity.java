package com.thafa.speedrun;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

private static final int OVERLAY_PERMISSION_REQUEST = 1001;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);
    layout.setGravity(Gravity.CENTER);
    layout.setPadding(32, 32, 32, 32);
    layout.setBackgroundColor(Color.rgb(18, 24, 38));

    TextView title = new TextView(this);
    title.setText("ThafaSpeedrun");
    title.setTextSize(28);
    title.setTextColor(Color.WHITE);
    title.setGravity(Gravity.CENTER);

    TextView subtitle = new TextView(this);
    subtitle.setText(
        "Minecraft Bedrock Speedrun Assistant\n\n" +
        "Aktifkan panel melayang untuk mulai."
    );
    subtitle.setTextSize(16);
    subtitle.setTextColor(Color.LTGRAY);
    subtitle.setGravity(Gravity.CENTER);
    subtitle.setPadding(0, 24, 0, 32);

    Button startButton = new Button(this);
    startButton.setText("Aktifkan Floating Overlay");
    startButton.setOnClickListener(view -> activateOverlay());

    layout.addView(title, new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    ));

    layout.addView(subtitle, new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    ));

    layout.addView(startButton, new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    ));

    setContentView(layout);
}

private void activateOverlay() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            && !Settings.canDrawOverlays(this)) {

        Intent intent = new Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:" + getPackageName())
        );

        startActivityForResult(
            intent,
            OVERLAY_PERMISSION_REQUEST
        );

        return;
    }

    startOverlayService();
}

@Override
protected void onActivityResult(
        int requestCode,
        int resultCode,
        Intent data) {

    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == OVERLAY_PERMISSION_REQUEST) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || Settings.canDrawOverlays(this)) {
            startOverlayService();
        } else {
            Toast.makeText(
                this,
                "Izin overlay belum diberikan.",
                Toast.LENGTH_LONG
            ).show();
        }
    }
}

private void startOverlayService() {
    Intent intent = new Intent(this, OverlayService.class);
    startService(intent);

    Toast.makeText(
        this,
        "ThafaSpeedrun overlay diaktifkan!",
        Toast.LENGTH_SHORT
    ).show();
}

}