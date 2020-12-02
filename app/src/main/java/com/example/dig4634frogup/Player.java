package com.example.dig4634frogup;

public class Player {
    private float xPos;
    private float yPos;
    private float xVel;
    private float yVel;
    private float jumpVel;
    private int rad;
    private static final float GRAVITY = 1.5f;

    public Player() {
        xPos = 540;
        yPos = 1000;
        rad = 100;
        yVel = 0.0f;
        jumpVel = -60.0f;
    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }

    public int getRadius() {
        return rad;
    }

    public void update(float acc_x, int w, int h, StandardPlatform p) {
        xPos -= acc_x*2.0f;
        yPos += yVel;
        yVel += GRAVITY;

        // check for collisions, probably pass in array of platforms

        if (yPos + rad >= h) { // detect ground
            yVel = jumpVel;
        }

        if (yVel >= 0.0f
                && yPos + rad >= p.getY() - (float)p.getHeight()/2 - 10
                && yPos + rad <= p.getY() - (float)p.getHeight()/2 + 10
                && xPos < p.getX() + (float)p.getWidth()/2
                && xPos > p.getX() - (float)p.getWidth()/2) {
            yVel = jumpVel;
        }
    }
}
