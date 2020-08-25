package com.example.flyingbird;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer background_music;
    private GameView gameView;
    private Handler handler=new Handler();
    private final static long TIMER_INTERVAL=30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView=new GameView(this);
        setContentView(gameView);

        background_music=MediaPlayer.create(MainActivity.this,R.raw.backgroundmusic);
        background_music.setLooping(true);
        background_music.start();
        Timer timer=new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });

            }
        },0,TIMER_INTERVAL);
    }
    protected void onPause() {
        super.onPause();
        background_music.release();
        finish();
    }
}

