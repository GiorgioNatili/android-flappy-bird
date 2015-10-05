package com.example.tc.flappybird;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.tc.flappybird.GameObjects.GameView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //makeAppFullScreen();
        GameView gameView = new GameView(this);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new ViewPager.LayoutParams());
        frameLayout.addView(gameView);
        setContentView(frameLayout);
    }

    private void makeAppFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
