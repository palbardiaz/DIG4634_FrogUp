package com.example.dig4634frogup;

import java.util.Random;

public class BoostPlatform {
    private float xPos;
    private float yPos;
    private int width;
    private int height;
    private static final int HEIGHT = 2040;
    private Random ran;


    public BoostPlatform(int x, int y) {
        xPos = x;
        yPos = y;
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
        if (yPos > 2100) {
            //xPos = 500;
            xPos = ran.nextInt(900) + 100;
            yPos = -1 * (ran.nextInt(4000) + 6000);
        }
        yPos -= cameraSpeed;
    }
}
