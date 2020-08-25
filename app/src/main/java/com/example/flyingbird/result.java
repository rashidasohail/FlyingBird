package com.example.flyingbird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView score_tv=(TextView) findViewById(R.id.score_tv);
        TextView highScore_tv=(TextView) findViewById(R.id.highScore_tv);

        int score= getIntent().getIntExtra("Score",0);
        score_tv.setText(score+"");

        SharedPreferences settings= getSharedPreferences("GAME DATA", Context.MODE_PRIVATE);
        int highScore=settings.getInt("HIGH_SCORE",0);
        if(score>highScore){
            highScore_tv.setText("High Score : "+score);
            //Save
            SharedPreferences.Editor editor= settings.edit();
            editor.putInt("HIGH_SCORE",score);
            editor.apply();

        }
        else{
            highScore_tv.setText("High Score :"+highScore);
        }

    }

    public void tryAgain(View view){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
    public void Exit(View view){
        finish();
        System.exit(0);
    }
}
