package com.example.tc.flappybird.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

// this object represents a top pipe and its complementary bottom pipe
public class PipePair {
    private final int WIDTH = 100;
    private final int PIPE_OPENING_SIZE = 350;

    private int pipeOpeningY; // measured from the top
    private float xPos;

    public PipePair(int initXPos, int pipeOpeningY) {
        this.xPos = initXPos;
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
    }
}
