package com.example.ga.flappybird.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

// this object represents a top pipe and its complementary bottom pipe
public class PipePair {

    private static final int PIPE_WIDTH = 100;
    private static final int PIPE_OPENING_SIZE = 350;
    private final int INIT_X_POS;

    private int mPipeHeight; // measured from the top, we randomize this for level design
    private float mPipeRight;
    private float mXPos;
    private float mDistanceTraveled = 0;
    private boolean mNextPipeCreated = false;
    private boolean isPassed = false;

    private RectF mTopHitbox;
    private RectF mBotHitbox;

    private int screenHeight;


    public PipePair(int initialX, int initialY, int screenHeight) {

        this.INIT_X_POS = initialX;
        this.mXPos = INIT_X_POS;
        this.mPipeHeight = initialY;
        this.screenHeight = screenHeight;

        mPipeRight = INIT_X_POS + PIPE_WIDTH;
        mTopHitbox = new RectF(INIT_X_POS, 0, mPipeRight, mPipeHeight);
        mBotHitbox = new RectF(INIT_X_POS, mPipeHeight + PIPE_OPENING_SIZE,
                mPipeRight, screenHeight);

    }

    public void drawSelf(Canvas canvas, Bitmap topBitmap,
                         Bitmap bottomBitmap, Paint paint) {

        canvas.drawBitmap(topBitmap, null, mTopHitbox, paint);
        canvas.drawBitmap(bottomBitmap, null, mBotHitbox, paint);

    }

    public void updatePosition(float time, int xVelocity) {

        mXPos = mXPos - xVelocity * time;
        mDistanceTraveled = Math.abs(mXPos - INIT_X_POS);
        updateHitBoxes();

    }

    private void updateHitBoxes() {

        mPipeRight = mXPos + PIPE_WIDTH;
        mTopHitbox.set(mXPos, 0, mPipeRight, mPipeHeight);
        mBotHitbox.set(mXPos, mPipeHeight + PIPE_OPENING_SIZE,
                mPipeRight, screenHeight);

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

    public static int getPipeWidth() {

        return PIPE_WIDTH;

    }

    public static int getPipeOpeningSize() {

        return PIPE_OPENING_SIZE;

    }

    public RectF getTopHitbox() {

        return this.mTopHitbox;

    }

    public RectF getBotHitbox() {

        return this.mBotHitbox;

    }

    public float getPipeRight() {
        return this.mPipeRight;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean update) {
        this.isPassed = update;
    }
}
