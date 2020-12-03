package com.example.dig4634frogup;

import android.util.Log;

public class Player {
    private float xPos;
    private float yPos;
    private float xVel;
    private float yVel;
    private static final float JUMP_VEL = -60.0f;
    private int rad;
    private static final float GRAVITY = 1.5f;

    public Player() {
        xPos = 540;
        yPos = 1000;
        rad = 100;
        yVel = 0.0f;
    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }

    public float getSpeed() {
        return yVel;
    }

    public int getRadius() {
        return rad;
    }

    public void update(float acc_x, int w, int h, StandardPlatform[] p, float cameraSpeed) {
        xPos -= acc_x*2.0f;
        yPos += yVel - cameraSpeed;
        yVel += GRAVITY;

        // check for collisions, probably pass in array of platforms

        for (int i= 0; i < p.length; i++) {
            if (yVel >= 0.0f
                    && yPos + rad >= p[i].getY() - (float)p[i].getHeight()/2 - 20
                    && yPos + rad <= p[i].getY() - (float)p[i].getHeight()/2 + 20
                    && xPos < p[i].getX() + (float)p[i].getWidth()/2 + 50
                    && xPos > p[i].getX() - (float)p[i].getWidth()/2- 50) {
                yVel = JUMP_VEL;
                //Log.d("Info" , p.getY())
            }
        }

        if (yPos + rad >= h) { // detect ground
            yVel = JUMP_VEL;
        }
    }
}
