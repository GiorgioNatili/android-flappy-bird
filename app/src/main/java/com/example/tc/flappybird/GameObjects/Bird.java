package com.example.tc.flappybird.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Bird {

    private final int OFFSET = 40;
    private final int STARTING_HEIGHT = 100;
    private final int SIZE_HEIGHT = 70;
    private final int SIZE_WIDTH = 100;

    private float yPosition;
    private float xPosition;
    private float yVelocity;

    private RectF hitbox;


    public Bird() {
        this.yPosition = STARTING_HEIGHT;
        this.yVelocity = 0;
    }

    public void drawSelf(Canvas canvas, Bitmap bitmap, Paint paint) {
        float left = OFFSET;
        float right = OFFSET + SIZE_WIDTH;
        float top = yPosition;
        float bot = yPosition + SIZE_HEIGHT;

        hitbox = new RectF(left, top, right, bot);
        canvas.drawBitmap(bitmap, null, hitbox, paint);
    }

    public void updatePosition(float time, int gravity, int xVelocity) {
        xPosition = xPosition + xVelocity * time;
        yVelocity = yVelocity + gravity * time;
        yPosition = yPosition + yVelocity * time;
    }

    public void setYVelocity(float newVelocity) {
        this.yVelocity = newVelocity;
    }

    public void resetState() {
        this.yPosition = 100;
        this.yVelocity = 0;
    }
}
