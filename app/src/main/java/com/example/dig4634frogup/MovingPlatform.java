package com.example.dig4634frogup;

import java.util.Random;

public class MovingPlatform {
    private float xPos;
    private float yPos;
    private float xVel;
    private int width;
    private int height;
    private static final float SPEED = 15.0f;
    private static final int HEIGHT = 2040;
    private Random ran;


    public MovingPlatform(int x, int y) {
        xPos = x;
        yPos = y;
        xVel = 20.0f;
        width = 200;
        height = 50;
        ran = new Random();

    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void update(float cameraSpeed) {
        if (xPos > 900) {
            xVel = -1*SPEED;
        } else if (xPos < 180) {
            xVel = SPEED;
        }

        if (yPos > 2100) {
            //xPos = 500;
            xPos = ran.nextInt(900) + 100;
            yPos = -1 * (ran.nextInt(300) + 50);
        }
        yPos -= cameraSpeed;
        xPos += xVel;
    }
}
