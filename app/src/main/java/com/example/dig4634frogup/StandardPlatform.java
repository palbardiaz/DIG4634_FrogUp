package com.example.dig4634frogup;

public class StandardPlatform {
    private float xPos;
    private float yPos;
    private int width;
    private int height;

    public StandardPlatform(int x, int y) {
        xPos = x;
        yPos = y;
        width = 200;
        height = 50;
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
}
