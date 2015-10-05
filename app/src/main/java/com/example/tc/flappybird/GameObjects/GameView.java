package com.example.tc.flappybird.GameObjects;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.example.tc.android_flappy_bird.R;

import java.util.Queue;

public class GameView extends View {

    private final int GRAVITY = 2000;
    private final int X_VELOCITY = 200;
    private final int X_DIST_BETWEEN_PIPES = 500; // five pipe widths

    private Paint paint;
    private Bitmap birdBitMap;
    private Bitmap backgroundBitMap;
    private Bitmap topPipeBitMap;
    private Bitmap botPipeBitMap;

    private Bird playerBird;
    private PipePair testPair;
    private Queue<PipePair> pipePairsOnScreen;

    private int screenHeight;
    private int screenWidth;

    private long lastFrame = -1;

    private int score = 0;

    public GameView(Context context) {
        super(context);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        this.screenHeight = displaymetrics.heightPixels;
        this.screenWidth = displaymetrics.widthPixels;
        this.paint = new Paint();
        this.playerBird = new Bird();
        this.testPair = new PipePair((int)(screenWidth * 1.5), 200);
        this.birdBitMap = BitmapFactory.decodeResource(getResources(),
                R.drawable.bird);
        this.backgroundBitMap = BitmapFactory.decodeResource(getResources(),
                R.drawable.background);
        this.topPipeBitMap = BitmapFactory.decodeResource(getResources(),
                R.drawable.obstacle_top);
        this.botPipeBitMap = BitmapFactory.decodeResource(getResources(),
                R.drawable.obstacle_bottom);
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
        score = 0;
        playerBird.resetState();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        playerBird.drawSelf(canvas, birdBitMap, paint);
        drawPipes(canvas);
        updatePositions();
        invalidate();
    }

    private void drawBackground(Canvas canvas) {
        RectF backgroundRect = new RectF(0, 0, screenWidth, screenHeight);
        canvas.drawBitmap(backgroundBitMap, null, backgroundRect, paint);
    }

    private void drawPipes(Canvas canvas) {
        testPair.drawSelf(canvas, topPipeBitMap, botPipeBitMap, screenHeight, paint);
        //for (PipePair pipePair : pipePairsOnScreen) {
        //    pipePair.drawSelf(canvas);
        //}
    }

    private void updatePositions() {
        long currentTime = System.currentTimeMillis();
        if (lastFrame != -1) {
            float time = (currentTime - lastFrame) / 1000f; // divide by 1000 to convert to seconds
            playerBird.updatePosition(time, GRAVITY, X_VELOCITY);
            testPair.updatePosition(time, X_VELOCITY);
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
