package com.example.dig4634frogup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CasualActivity extends AppCompatActivity implements SensorEventListener, SurfaceHolder.Callback{

    SurfaceHolder holder=null;

    int score = 0;

    CasualAnimator my_animator;

    float cameraSpeed = 0.0f;

    Player player = new Player();
    float acc_x = 0.0f;
    Paint player_paint;
    Paint platform_paint;
    Paint score_paint;
    Paint boost_paint;
    Bitmap bg;
    Bitmap sitFrog;
    Bitmap downLeftFrog;
    Bitmap downRightFrog;
    Bitmap floatFrogLeft;
    Bitmap floatFrogRight;
    Bitmap platform;
    Bitmap boost;

    StandardPlatform[] platforms;
    BoostPlatform boostPlatform;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casual);

        player_paint=new Paint();
        player_paint.setColor(Color.GREEN);
        player_paint.setStyle(Paint.Style.FILL);

        platform_paint=new Paint();
        platform_paint.setColor(Color.BLACK);
        platform_paint.setStyle(Paint.Style.FILL);

        boost_paint=new Paint();
        boost_paint.setColor(Color.CYAN);
        boost_paint.setStyle(Paint.Style.FILL);

        score_paint=new Paint();
        score_paint.setColor(Color.BLACK);
        score_paint.setTextSize(90);
        //score_paint.setStyl

        platforms = new StandardPlatform[5];
        platforms[0] = new StandardPlatform(540, 1500);
        platforms[1] = new StandardPlatform(800, 1000);
        platforms[2] = new StandardPlatform(300, 500);
        platforms[3] = new StandardPlatform(400, 100);
        platforms[4] = new StandardPlatform(300, -300);

        boostPlatform = new BoostPlatform(400, -1000);

        bg = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bglight),Resources.getSystem().getDisplayMetrics().widthPixels,(int)(Resources.getSystem().getDisplayMetrics().heightPixels-800),false);
        sitFrog = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.froggie),player.getRadius()*2,player.getRadius()*2,false);
        downLeftFrog = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.froggo_laying),player.getRadius()*2,player.getRadius()*2,false);
        downRightFrog = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.froggo_laying),player.getRadius()*-2,player.getRadius()*2,false);
        floatFrogLeft = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.froggo_dancing),player.getRadius()*-2,player.getRadius()*2,false);
        floatFrogRight = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.froggo_dancing),player.getRadius()*2,player.getRadius()*2,false);
        platform = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plat), platforms[0].getWidth(), platforms[0].getHeight(), false);
        boost = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.boost), boostPlatform.getWidth(), boostPlatform.getHeight(), false);

        SensorManager manager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer!=null){
            manager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL,SensorManager.SENSOR_DELAY_UI);
        }

        SurfaceView my_surface=findViewById(R.id.surfaceView);
        my_surface.getHolder().addCallback(this);


        my_animator=new CasualAnimator(this);
        my_animator.start();
    }

    public void update(int width, int height){

        if (player.isDead()) {
            if (score > GlobalVariables.highscore_casual) {
                GlobalVariables.highscore_casual = score;
            }

            Intent intent = new Intent(getBaseContext(), GameOverActivity.class);
            intent.putExtra("SCORE", Integer.toString(score));
            intent.putExtra("LEVEL", "CASUAL");
            startActivity(intent);
            my_animator.finish();
            finish();
            //Log.d("DEATH", "DEADDDD");
        }

        score -= cameraSpeed/10;

        player.update(acc_x, width, height, platforms, boostPlatform, cameraSpeed);

        for (int i = 0; i < platforms.length; i++) {
            platforms[i].update(cameraSpeed);
        }

        boostPlatform.update(cameraSpeed);

        if (player.getY() < 500 && player.getSpeed() < 0) {
            cameraSpeed = player.getSpeed();
            //Log.d("Speed", String.valueOf(cameraSpeed));
        } else {
            cameraSpeed = 0.0f;
        }

    }

    public void draw(){

        if(holder==null)return;

        Canvas c=holder.lockCanvas();

        update(c.getWidth(),c.getHeight());
        Log.d("Example", String.valueOf(c.getWidth()));

        c.drawColor(Color.rgb(200,230,250));

        c.drawBitmap(bg, 0, 800, null);


        // draw platforms
        for (int i = 0; i < platforms.length; i++) {
            /*c.drawRect(platforms[i].getX() - (float)platforms[i].getWidth()/2,
                    platforms[i].getY() + (float)platforms[i].getHeight()/2,
                    platforms[i].getX() + (float)platforms[i].getWidth()/2,
                    platforms[i].getY() - (float)platforms[i].getHeight()/2,
                    platform_paint);*/
            c.drawBitmap(platform, platforms[i].getX() - (float)platforms[i].getWidth()/2, platforms[i].getY() - (float)platforms[i].getHeight()/2, null);
        }

        // draw boost platforms
        /*c.drawRect(boostPlatform.getX() - (float)boostPlatform.getWidth()/2,
                boostPlatform.getY() + (float)boostPlatform.getHeight()/2,
                boostPlatform.getX() + (float)boostPlatform.getWidth()/2,
                boostPlatform.getY() - (float)boostPlatform.getHeight()/2,
                boost_paint);*/
        c.drawBitmap(boost, boostPlatform.getX() - (float)boostPlatform.getWidth()/2, boostPlatform.getY() - (float)boostPlatform.getHeight()/2, null);

        // draw player
        /*c.drawRect(player.getX() - player.getRadius(),
                player.getY() + player.getRadius(),
                player.getX() + player.getRadius(),
                player.getY() - player.getRadius(),
                player_paint);*/

        if (player.getSpeed() < -20 ) {
            c.drawBitmap(sitFrog, player.getX() - player.getRadius(), player.getY() - player.getRadius(), null);
        } else if (player.getSpeed() < 20) {
            if (acc_x > 1) {
                c.drawBitmap(floatFrogLeft, player.getX() - player.getRadius(), player.getY() - player.getRadius(), null);
            } else {
                c.drawBitmap(floatFrogRight, player.getX() - player.getRadius(), player.getY() - player.getRadius(), null);
            }
        } else {
            if (acc_x > 1) {
                c.drawBitmap(downRightFrog, player.getX() - player.getRadius(), player.getY() - player.getRadius(), null);
            } else {
                c.drawBitmap(downLeftFrog, player.getX() - player.getRadius(), player.getY() - player.getRadius(), null);
            }
        }


        c.drawText("Score: "  + Integer.toString(score), 30 , 100, score_paint);

        holder.unlockCanvasAndPost(c);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        acc_x = sensorEvent.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d("Example","Surface is created");
        holder=surfaceHolder;

        draw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.d("Example","Surface changed");
        holder=surfaceHolder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        holder=null;
    }

    @Override
    public void onDestroy(){

        my_animator.finish();
        SensorManager manager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        manager.unregisterListener(this,manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));

        super.onDestroy();
    }
}
