package com.example.ga.flappybird.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

// this object represents a top pipe and its complementary bottom pipe
public class PipePair {

    private final int WIDTH = 100;
    private final int PIPE_OPENING_SIZE = 350;
    private final int INIT_X_POS;

    private int pipeStartingPosition; // measured from the top, we randomize this for level design
    private float x;
    private float distanceTraveled = 0;
    private boolean nextPipeCreated = false;

    public PipePair(int initXPos, int initialY) {

        this.INIT_X_POS = initXPos;
        this.x = INIT_X_POS;
        this.pipeStartingPosition = initialY;

    }

    public void drawSelf(Canvas canvas, Bitmap topBitmap,
                         Bitmap bottomBitmap, int screenHeight, Paint paint) {

        float pipeRight = x + WIDTH;

        RectF top       = new RectF(x, 0, pipeRight, pipeStartingPosition);
        RectF bottom    = new RectF(x, pipeStartingPosition + PIPE_OPENING_SIZE, pipeRight, screenHeight);

        canvas.drawBitmap(topBitmap, null, top, paint);
        canvas.drawBitmap(bottomBitmap, null, bottom, paint);

    }

    public void updatePosition(float time, int xVelocity) {

        x = x - xVelocity * time;
        distanceTraveled = Math.abs(x - INIT_X_POS);

    }

    public float getXPos() {

        return this.x;

    }

    public float getDistanceTraveled() {

        return this.distanceTraveled;

    }

    public void setNextPipeCreated(boolean update) {

        nextPipeCreated = update;

    }

    public boolean getNextPipeCreated() {

        return this.nextPipeCreated;

    }
}
