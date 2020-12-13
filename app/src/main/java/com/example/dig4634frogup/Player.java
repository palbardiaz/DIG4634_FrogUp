package com.example.dig4634frogup;

import android.util.Log;

import java.io.IOException;

public class Player {
    private float xPos;
    private float yPos;
    private float xVel;
    private float yVel;
    private static final float JUMP_VEL = -60.0f;
    private static final float BOOST_VEL = -100.0f;
    private int rad;
    private static final float GRAVITY = 1.5f;
    private boolean alive = true;
    private boolean stunned = false;

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

    public boolean isDead() {return !alive;}

    public void update(float acc_x, int w, int h, StandardPlatform[] p, BoostPlatform b, float cameraSpeed) {

        if (xPos > 1080) {
            xPos = 0;
        } else if (xPos < 0) {
            xPos = 1080;
        }

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
                //GlobalVariables.jumpSound.reset();
                GlobalVariables.jumpSound.play(GlobalVariables.jumpSoundId, 1, 1, 1, 0, 1f);
                //GlobalVariables.jumpSound.start();
                //Log.d("Info" , p.getY())
            }
        }

        if (yVel >= 0.0f
                && yPos + rad >= b.getY() - (float)b.getHeight()/2 - 20
                && yPos + rad <= b.getY() - (float)b.getHeight()/2 + 20
                && xPos < b.getX() + (float)b.getWidth()/2 + 50
                && xPos > b.getX() - (float)b.getWidth()/2- 50) {
            yVel = BOOST_VEL;
            GlobalVariables.boostSound.play(GlobalVariables.boostSoundId, 1, 1, 1, 0, 1f);
            //GlobalVariables.boostSound.start();
            //Log.d("Info" , p.getY())
        }

        if (yPos + rad >= h) { // detect ground
            alive = false;
        }
    }

    public void update(float acc_x, int w, int h, StandardPlatform[] p, BoostPlatform b, MovingPlatform[] m, float cameraSpeed) {

        if (xPos > 1080) {
            xPos = 0;
        } else if (xPos < 0) {
            xPos = 1080;
        }

        yPos += yVel - cameraSpeed;
        yVel += GRAVITY;

        if (!stunned) {
            xPos -= acc_x*2.0f;

            // check for collisions, probably pass in array of platforms

            for (int i= 0; i < p.length; i++) {
                if (yVel >= 0.0f
                        && yPos + rad >= p[i].getY() - (float)p[i].getHeight()/2 - 20
                        && yPos + rad <= p[i].getY() - (float)p[i].getHeight()/2 + 20
                        && xPos < p[i].getX() + (float)p[i].getWidth()/2 + 50
                        && xPos > p[i].getX() - (float)p[i].getWidth()/2- 50) {
                    yVel = JUMP_VEL;
                    GlobalVariables.jumpSound.play(GlobalVariables.jumpSoundId, 1, 1, 1, 0, 1f);
                    //Log.d("Info" , p.getY())
                }
            }

            for (int i= 0; i < m.length; i++) {
                if (yVel >= 0.0f
                        && yPos + rad >= m[i].getY() - (float)m[i].getHeight()/2 - 20
                        && yPos + rad <= m[i].getY() - (float)m[i].getHeight()/2 + 20
                        && xPos < m[i].getX() + (float)m[i].getWidth()/2 + 50
                        && xPos > m[i].getX() - (float)m[i].getWidth()/2- 50) {
                    yVel = JUMP_VEL;
                    GlobalVariables.jumpSound.play(GlobalVariables.jumpSoundId, 1, 1, 1, 0, 1f);
                    //Log.d("Info" , p.getY())
                }
            }

            if (yVel >= 0.0f
                    && yPos + rad >= b.getY() - (float)b.getHeight()/2 - 20
                    && yPos + rad <= b.getY() - (float)b.getHeight()/2 + 20
                    && xPos < b.getX() + (float)b.getWidth()/2 + 50
                    && xPos > b.getX() - (float)b.getWidth()/2- 50) {
                yVel = BOOST_VEL;
                GlobalVariables.boostSound.play(GlobalVariables.boostSoundId, 1, 1, 1, 0, 1f);
                //GlobalVariables.boostSound.start();
                //Log.d("Info" , p.getY())
            }
        }

        if (yPos + rad >= h) { // detect ground
            alive = false;
        }
    }

    public void checkBounds() {
        if (xPos + rad > 1080 || xPos - rad < 0) {
            xVel = 0;
            stunned = true;
        }
    }
}
