package com.rodo.pointlessbutton;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    View decorView;

    FloatingActionButton pointlessButton;
    Button inspirationButton;
    SoundPref soundPref;
    CheckBox soundCheckBox;

    boolean pointlessButtonTouch = false;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pointlessButton = findViewById(R.id.pointlessButton);
        inspirationButton = findViewById(R.id.inspirationButton);
        soundCheckBox = findViewById(R.id.soundCheckBox);


        soundPref = new SoundPref(this);
        final MediaPlayer click_in = MediaPlayer.create(this, R.raw.in);
        final MediaPlayer click_out = MediaPlayer.create(this, R.raw.out);


        pointlessButton.setOnClickListener(v -> {
            if (soundPref.ButtonSound()) {
                click_out.start();
                this.pointlessButtonTouch = false;
            }
        });

        pointlessButton.setOnTouchListener((v, event) -> {
            if (soundPref.ButtonSound() && !pointlessButtonTouch) {
                click_in.start();
                this.pointlessButtonTouch = true;
            }
            return false;
        });


        String inspiString = "watch?v=IYnsfV5N2n8&";

        inspirationButton.setOnClickListener(v -> {
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + inspiString));
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/" + inspiString));
            try {
                startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                startActivity(browserIntent);
            }

        });

        soundCheckBox.setChecked(soundPref.ButtonSound());
        soundCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> soundPref.setButtonSound(isChecked));


        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(visibility -> {
            if (visibility == 0)
                decorView.setSystemUiVisibility(hideSystemBars());
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    private int hideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }
}