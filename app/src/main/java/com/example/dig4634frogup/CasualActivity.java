package com.example.dig4634frogup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CasualActivity extends AppCompatActivity implements SensorEventListener, SurfaceHolder.Callback{

    SurfaceHolder holder=null;

    CasualAnimator my_animator;

    Player player = new Player();
    float acc_x = 0.0f;
    Paint player_paint;
    Paint platform_paint;

    StandardPlatform platform;

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

        platform = new StandardPlatform(450, 900);

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

        player.update(acc_x, width, height, platform);

    }

    public void draw(){

        if(holder==null)return;

        Canvas c=holder.lockCanvas();

        update(c.getWidth(),c.getHeight());
        Log.d("Example", String.valueOf(c.getWidth()));

        c.drawColor(Color.rgb(250,250,250));

        // draw player
        c.drawRect(player.getX() - player.getRadius(),
                player.getY() + player.getRadius(),
                player.getX() + player.getRadius(),
                player.getY() - player.getRadius(),
                player_paint);

        // draw platform
        c.drawRect(platform.getX() - platform.getWidth()/2,
                platform.getY() + platform.getHeight()/2,
                platform.getX() + platform.getWidth()/2,
                platform.getY() - platform.getHeight()/2,
                platform_paint);

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
