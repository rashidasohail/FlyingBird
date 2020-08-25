package com.example.flyingbird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
    public void startGame(View view){
        Intent intent=new Intent(start.this,MainActivity.class);
        startActivity(intent);
    }
}

