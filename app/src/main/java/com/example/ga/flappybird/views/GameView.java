package com.example.ga.flappybird.views;

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

import com.example.ga.flappybird.R;
import com.example.ga.flappybird.model.Bird;
import com.example.ga.flappybird.model.PipePair;

import java.util.LinkedList;
import java.util.Random;


public class GameView extends View {

    private final int GRAVITY = 2000;
    private final int X_VELOCITY = 200;
    private final int X_DIST_BETWEEN_PIPES = PipePair.getPipeWidth() * 5;

    private final int PIPE_DEATH_POS = -100;
    private int pipeStartingPosition;


    private Paint paint;
    private Bitmap birdBitMap;
    private Bitmap backgroundBitMap;
    private Bitmap topPipeBitMap;
    private Bitmap botPipeBitMap;

    private Bird bird;

    private LinkedList<PipePair> pipePairs;

    private Random random;
    private int upperBound;

    private RectF backgroundRect;

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

        random = new Random(System.currentTimeMillis());
        upperBound = screenHeight - PipePair.getPipeOpeningSize();

        this.paint = new Paint();
        this.bird = new Bird();

        initBitmaps();
        resetGameState();

    }

    private void initBitmaps() {

        this.birdBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
        this.backgroundBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        this.topPipeBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.obstacle_top);
        this.botPipeBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.obstacle_bottom);

    }

    // called on initial startup
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);

        screenHeight = h;
        screenWidth = w;

        backgroundRect = new RectF(0, 0, screenWidth, screenHeight);

    }

    private void resetGameState() {

        lastFrame = -1;
        score = 0;

        bird.resetState();

        pipeStartingPosition = (int)(screenWidth * 1.2);
        pipePairs = new LinkedList<PipePair>();

        int startingHeight = random.nextInt(upperBound);
        PipePair firstPipes = new PipePair(pipeStartingPosition, startingHeight);
        pipePairs.add(firstPipes);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        drawBackground(canvas);
        bird.drawSelf(canvas, birdBitMap, paint);

        drawPipes(canvas);
        updatePositions();

        invalidate();

    }

    private void drawBackground(Canvas canvas) {

        canvas.drawBitmap(backgroundBitMap, null, backgroundRect, paint);

    }

    private void drawPipes(Canvas canvas) {

        for (PipePair pipePair : pipePairs) {

            pipePair.drawSelf(canvas, topPipeBitMap, botPipeBitMap, screenHeight, paint);

        }

    }

    private void updatePositions() {

        long currentTime = System.currentTimeMillis();

        if (lastFrame != -1) {

            float time = (currentTime - lastFrame) / 1000f; // divide by 1000 to convert to seconds
            bird.updatePosition(time, GRAVITY, X_VELOCITY);
            updatePipesOnScreen(time);

        }

        lastFrame = currentTime;

    }

    private void updatePipesOnScreen(float time) {

        for (int i = 0; i < pipePairs.size(); i++) {

            PipePair pointer = pipePairs.get(i);
            pointer.updatePosition(time, X_VELOCITY);

            if (!pointer.getNextPipeCreated() && (int)pointer.getDistanceTraveled() > X_DIST_BETWEEN_PIPES) {

                int randomHeight = random.nextInt(upperBound);
                pipePairs.add(new PipePair(pipeStartingPosition, randomHeight));
                pointer.setNextPipeCreated(true);

            }

            if (pipePairs.peek().getXPos() < PIPE_DEATH_POS) {

                pipePairs.remove();

            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            bird.setVerticalSpeed(-800);

        }

        return true;

    }
}
