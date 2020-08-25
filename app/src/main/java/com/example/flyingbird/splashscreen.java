package com.example.flyingbird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class splashscreen extends AppCompatActivity {

    MediaPlayer background_music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        background_music=MediaPlayer.create(splashscreen.this,R.raw.backgroundmusic);
        background_music.start();

        Thread thread=new Thread(){
            @Override
            public void run() {
                try{
                    sleep(5000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent=new Intent(splashscreen.this,start.class);
                    startActivity(intent);

                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        background_music.release();
        finish();
    }
}
