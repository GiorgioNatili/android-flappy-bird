package com.example.ga.flappybird.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Bird {

    private final int OFFSET = 40;
    private final int STARTING_HEIGHT = 100;
    private final int SIZE_HEIGHT = 70;
    private final int SIZE_WIDTH = 100;

    private float y;
    private float x;
    private float mVerticalVelocity;

    private RectF hitbox;


    public Bird() {

        this.y = STARTING_HEIGHT;
        this.mVerticalVelocity = 0;

    }

    public void drawSelf(Canvas canvas, Bitmap bitmap, Paint paint) {

        float left = OFFSET;
        float right = OFFSET + SIZE_WIDTH;
        float top = y;
        float bot = y + SIZE_HEIGHT;

        hitbox = new RectF(left, top, right, bot);
        canvas.drawBitmap(bitmap, null, hitbox, paint);

    }

    public void updatePosition(float time, int gravity, int xVelocity) {

        x = x + xVelocity * time;
        mVerticalVelocity = mVerticalVelocity + gravity * time;
        y = y + mVerticalVelocity * time;

    }

    public void setVerticalSpeed(float newVelocity) {

        this.mVerticalVelocity = newVelocity;

    }

    public void resetState() {

        this.y = 100;
        this.mVerticalVelocity = 0;

    }
}
