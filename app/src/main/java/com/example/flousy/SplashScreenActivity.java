package com.example.flousy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import flousy.db.DBManager;
import flousy.session.Session;

public class SplashScreenActivity extends Activity {

    private static final int SPLASH_TIME_OUT = 3000;
    private Handler handler;
    private Runnable runnable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);

        createLogo();
    }

    @Override
    protected void onStart() {
        super.onStart();

        DBManager.start(this);
        Session.start(this);

        if (Session.isUserLoggedIn()) {
            goToActivity(HomeActivity.class, SPLASH_TIME_OUT);
        } else {
            goToActivity(UserLogActivity.class, SPLASH_TIME_OUT);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        stopGoToActivity();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        stopGoToActivity();
    }

    private void createLogo() {
        ImageView imageView = (ImageView) findViewById(R.id.splashscreen_imageview_logo);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_app_logo));
    }

    private void goToActivity(Class<?> activityClass, int timeOut) {
        final Intent intent = new Intent(this, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        this.runnable = new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
            }
        };

        this.handler = new Handler();
        this.handler.postDelayed(this.runnable, timeOut);
    }

    private void stopGoToActivity() {
        try {
            this.handler.removeCallbacks(this.runnable);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}