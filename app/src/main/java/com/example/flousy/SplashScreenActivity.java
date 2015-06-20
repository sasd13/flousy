package com.example.flousy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import flousy.data.dao.DataAccessorManager;
import flousy.session.Session;

public class SplashScreenActivity extends Activity {

    private static final int SPLASH_TIME_OUT = 3000;
    private Handler handler;
    private Runnable runnable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);

        ImageView imageView = (ImageView) findViewById(R.id.splashscreen_imageview_logo);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_app_logo));
    }

    @Override
    protected void onStart() {
        super.onStart();

        DataAccessorManager.start(this);
        Session.start(this);

        if (Session.isLogged()) {
            attachActivity(MenuActivity.class, SPLASH_TIME_OUT);
        } else {
            attachActivity(LogInActivity.class, SPLASH_TIME_OUT);
        }
    }

    /**
     * Stop calling post delayed activity on user action
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        detachActivity();
        finish();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        detachActivity();
        finish();
    }

    private void attachActivity(Class<?> activityClass, int timeOut) {
        final Intent intent = new Intent(this, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        this.runnable = new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        };

        this.handler = new Handler();
        this.handler.postDelayed(this.runnable, timeOut);
    }

    private void detachActivity() {
        try {
            this.handler.removeCallbacks(this.runnable);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}