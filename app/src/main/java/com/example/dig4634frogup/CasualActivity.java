package com.example.dig4634frogup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casual);


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



    }

    public void draw(){

        if(holder==null)return;

        Canvas c=holder.lockCanvas();

        update(c.getWidth(),c.getHeight());

        c.drawColor(Color.rgb(0,200,0));

        holder.unlockCanvasAndPost(c);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

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
