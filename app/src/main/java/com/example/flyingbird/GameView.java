package com.example.flyingbird;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;


public class GameView extends View {

    //Canvas
    private int canvas_width;
    private int canvas_height;

    //Bird
    private Bitmap bird[]=new Bitmap[2];
    private int birdX = 10;
    private int birdY;
    private int bird_speed;

    //Blue Ball
    private int blueX;
    private int blueY;
    private int blue_speed=15;
    private Paint bluePaint= new Paint();

    //Black Ball
    private int blackX;
    private int blackY;
    private int black_speed=20;
    private Paint blackPaint= new Paint();

    //Black Ball2
    private int blackX2;
    private int blackY2;
    private int black_speed2=20;
    private Paint blackPaint2= new Paint();

    //Background
    private Bitmap background_img;

    //Score
    private Paint scorePaint=new Paint();
    private int score;

    //Level
    private Paint levelPaint=new Paint();

    //Life
    private Bitmap life[]=new Bitmap[2];
    private int life_count;

    //Sound
    private sound sound_player=new sound(getContext());

    //Status check
    private boolean touch_flg=false;
    public GameView(Context context) {
        super(context);
        bird[0]= BitmapFactory.decodeResource(getResources(),R.drawable.bird1);
        bird[1]= BitmapFactory.decodeResource(getResources(),R.drawable.bird2);

        background_img= BitmapFactory.decodeResource(getResources(),R.drawable.bg);

        bluePaint.setColor(Color.BLUE);
        bluePaint.setAntiAlias(false);

        blackPaint.setColor(Color.BLACK);
        blackPaint.setAntiAlias(false);

        blackPaint2.setColor(Color.BLACK);
        blackPaint2.setAntiAlias(false);

        scorePaint.setTextSize(32);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        levelPaint.setColor(Color.DKGRAY);
        levelPaint.setTextSize(32);
        levelPaint.setTypeface(Typeface.DEFAULT_BOLD);
        levelPaint.setTextAlign(Paint.Align.CENTER);
        levelPaint.setAntiAlias(true);

        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.heart);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heart_g);

        //First position
        birdY=500;
        score = 0;
        life_count = 3;

    }

    @Override
    protected void onDraw(final Canvas canvas) {
        canvas_width=canvas.getWidth();
        canvas_height=canvas.getHeight();
        canvas.drawBitmap(background_img,0,0,null);

        //Bird
        int minBirdY=bird[0].getHeight();
        int maxBirdY=canvas_height-bird[0].getHeight()*3;
        birdY+=bird_speed;
        if(birdY<minBirdY){
            birdY=minBirdY;
        }
        if(birdY>maxBirdY){
            birdY=maxBirdY;
        }
        bird_speed+=2;
        if(touch_flg){
            //flap wings
            canvas.drawBitmap(bird[1],birdX,birdY,null);
            touch_flg=false;
        }
        else{
            canvas.drawBitmap(bird[0],birdX,birdY,null);
        }

        //Blue
        blueX -= blue_speed;

        if(hitCheck(blueX,blueY)){
                score +=10;
                blueX = -100;
                sound_player.playHitSound();

        }

        if(blueX<0){
            blueX =canvas_width + 20;
            blueY =(int) Math.floor(Math.random()*(maxBirdY-minBirdY))+minBirdY;

        }
        canvas.drawCircle(blueX,blueY,10,bluePaint);

        String level;

                                                       //level1

        if(score==0||score<=100){

            level="Lv.1";
           // canvas.drawText(null,20,100,message);
            canvas.drawText(level,canvas.getWidth()/2,60,levelPaint);
            //Black
            blackX -= black_speed;

            if (hitCheck(blackX, blackY)) {
                blackX = -100;
                life_count--;
                sound_player.playOverSound();
                if (life_count == 0) {
                    //GameOVer
                    GameOver();

                }
            }
            if (blackX < 0) {
                blackX = canvas_width + 200;
                blackY = (int) Math.floor(Math.random() * (maxBirdY - minBirdY)) + minBirdY;

            }
            canvas.drawCircle(blackX, blackY, 20, blackPaint);
        }

                                                         //level 2

        if(score>100&&score<=250){

            level="Lv.2";

            canvas.drawText(level,canvas.getWidth()/2,60,levelPaint);

            blackX -= black_speed;
            blackX2 -= black_speed2;

            if(hitCheck(blackX,blackY)){
                blackX = -100;
                life_count--;
                sound_player.playOverSound();
                if(life_count==0){
                    //GameOVer
                    GameOver();

                }
            }
            if(hitCheck(blackX2,blackY2)){
                blackX2=-100;
                life_count--;
                sound_player.playOverSound();
                if(life_count==0){
                    //GameOVer
                    GameOver();

                }
            }
            if(blackX<0){
                blackX = canvas_width + 100;
                blackY = (int) Math.floor(Math.random()*(maxBirdY-minBirdY))+minBirdY;

            }
            canvas.drawCircle(blackX,blackY,20,blackPaint);
            if(blackX2<0){
                blackX2 = canvas_width + 90;
                blackY2 = (int) Math.floor(Math.random()*(maxBirdY-minBirdY))+minBirdY;

            }
            canvas.drawCircle(blackX2,blackY2,20,blackPaint2);
        }
                                                             //level 3
        if(score>250){

            level="Lv.3";
            black_speed=23;
            black_speed2=23;

            canvas.drawText(level,canvas.getWidth()/2,60,levelPaint);
            blackX -= black_speed;
            blackX2 -= black_speed2;

            if(hitCheck(blackX,blackY)){
                blackX = -100;
                life_count--;
                sound_player.playOverSound();
                if(life_count==0){
                    //GameOVer
                    GameOver();

                }
            }
            if(hitCheck(blackX2,blackY2)){
                blackX2=-100;
                life_count--;
                sound_player.playOverSound();
                if(life_count==0){
                    //GameOVer
                    GameOver();

                }
            }
            if(blackX<0){
                blackX = canvas_width + 100;
                blackY = (int) Math.floor(Math.random()*(maxBirdY-minBirdY))+minBirdY;

            }
            canvas.drawCircle(blackX,blackY,20,blackPaint);
            if(blackX2<0){
                blackX2 = canvas_width + 90;
                blackY2 = (int) Math.floor(Math.random()*(maxBirdY-minBirdY))+minBirdY;

            }
            canvas.drawCircle(blackX2,blackY2,20,blackPaint2);
        }


        //SCORE
        canvas.drawText("Score : "+score,20,60,scorePaint);

        //LIFE
        for (int i=0;i<3;i++){
            int x=(int)(510 + life[0].getWidth()*1.5*i);
            int y=30;
            if(i<life_count){
                canvas.drawBitmap(life[0],x,y,null);
            }
            else{
                canvas.drawBitmap(life[1],x,y,null);
            }

        }



    }


    private boolean hitCheck(int x, int y) {
        if(birdX < x && x < (birdX+bird[0].getWidth()) && birdY < y && y < (birdY+bird[0].getHeight())) {
             return true;
        }
        return false;
    }


    public void GameOver(){
        Intent intent=new Intent(getContext(),result.class);
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("Score",score);
        getContext().startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
           touch_flg=true;
           bird_speed=-20;

        }
        return true;
    }

}
