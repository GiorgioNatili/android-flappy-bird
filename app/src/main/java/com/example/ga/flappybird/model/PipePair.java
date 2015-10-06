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
    private float mXPos;
    private float mDistanceTraveled = 0;
    private boolean mNextPipeCreated = false;

    public PipePair(int initalXPos, int initialY) {

        this.INIT_X_POS = initalXPos;
        this.mXPos = INIT_X_POS;
        this.pipeStartingPosition = initialY;

    }

    public void drawSelf(Canvas canvas, Bitmap topBitmap,
                         Bitmap bottomBitmap, int screenHeight, Paint paint) {

        float pipeRight = mXPos + WIDTH;

        RectF top       = new RectF(mXPos, 0, pipeRight, pipeStartingPosition);
        RectF bottom    = new RectF(mXPos, pipeStartingPosition + PIPE_OPENING_SIZE, pipeRight, screenHeight);

        canvas.drawBitmap(topBitmap, null, top, paint);
        canvas.drawBitmap(bottomBitmap, null, bottom, paint);

    }

    public void updatePosition(float time, int xVelocity) {

        mXPos = mXPos - xVelocity * time;
        mDistanceTraveled = Math.abs(mXPos - INIT_X_POS);

    }

    public float getXPos() {

        return this.mXPos;

    }

    public float getDistanceTraveled() {

        return this.mDistanceTraveled;

    }

    public void setNextPipeCreated(boolean update) {

        mNextPipeCreated = update;

    }

    public boolean getNextPipeCreated() {

        return this.mNextPipeCreated;

    }
}
