package com.example.tc.android_flappy_bird.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import com.example.tc.android_flappy_bird.R;

public class GameView extends View {
    private final int GRAVITY = 2000;

    private Paint paint;
    private Bitmap birdBitMap;
    private Bitmap backGroundBitMap;

    private Bird playerBird;

    private int screenHeight;
    private int screenWidth;

    private long lastFrame = -1;

    public GameView(Context context) {
        super(context);
        this.paint = new Paint();
        this.playerBird = new Bird();
        this.birdBitMap = BitmapFactory.decodeResource(getResources(),
                R.drawable.bird);
        this.backGroundBitMap = BitmapFactory.decodeResource(getResources(),
                R.drawable.background);
    }

    // called on initial startup
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenHeight = h;
        screenWidth = w;
        resetGameState();
    }

    private void resetGameState() {
        lastFrame = -1;
        playerBird.resetState();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        playerBird.drawSelf(canvas, birdBitMap, paint);
        updatePositions();
        invalidate();
    }

    private void drawBackground(Canvas canvas) {
        RectF backgroundRect = new RectF(0, 0, screenWidth, screenHeight);
        canvas.drawBitmap(backGroundBitMap, null, backgroundRect, paint);
    }

    private void updatePositions() {
        long currentTime = System.currentTimeMillis();
        if (lastFrame != -1) {
            float time = (currentTime - lastFrame) / 1000f; // divide by 1000 to convert to seconds
            playerBird.updatePosition(time, GRAVITY);
        }
        lastFrame = currentTime;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            playerBird.setYVelocity(-800);
        }
        return true;
    }
}
