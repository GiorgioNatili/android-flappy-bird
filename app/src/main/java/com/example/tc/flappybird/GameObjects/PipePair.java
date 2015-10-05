package com.example.tc.flappybird.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

// this object represents a top pipe and its complementary bottom pipe
public class PipePair {
    private final int WIDTH = 100;
    private final int PIPE_OPENING_SIZE = 350;
    private final int INIT_X_POS;

    private int pipeOpeningY; // measured from the top, we randomize this for level design
    private float xPos;
    private float distanceTraveled = 0;
    private boolean hasBirthedNextPipe = false;

    public PipePair(int initXPos, int pipeOpeningY) {
        this.INIT_X_POS = initXPos;
        this.xPos = INIT_X_POS;
        this.pipeOpeningY = pipeOpeningY;
    }

    public void drawSelf(Canvas canvas, Bitmap topPipeBitMap,
                         Bitmap botPipeBitMap, int screenHeight, Paint paint) {
        float pipeRight = xPos + WIDTH;
        RectF topRect = new RectF(xPos, 0, pipeRight, pipeOpeningY);
        canvas.drawBitmap(topPipeBitMap, null, topRect, paint);
        RectF botRect = new RectF(xPos, pipeOpeningY + PIPE_OPENING_SIZE, pipeRight, screenHeight);
        canvas.drawBitmap(botPipeBitMap, null, botRect, paint);
    }

    public void updatePosition(float time, int xVelocity) {
        xPos = xPos - xVelocity * time;
        distanceTraveled = Math.abs(xPos - INIT_X_POS);
    }

    public float getXPos() {
        return this.xPos;
    }

    public float getDistanceTraveled() {
        return this.distanceTraveled;
    }

    public void setBirthed(boolean update) {
        hasBirthedNextPipe = update;
    }

    public boolean getBirthed() {
        return this.hasBirthedNextPipe;
    }
}
