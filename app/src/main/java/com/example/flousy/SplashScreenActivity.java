package com.example.flousy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import flousy.db.DataAccessorFactory;
import flousy.session.Session;

public class SplashScreenActivity extends Activity {

    private static final int SPLASHSCREEN_TIMEOUT = 3000;

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

        DataAccessorFactory.get().init(this);
        Session.start(this);

        if (Session.isUserLoggedIn()) {
            goToActivity(HomeActivity.class, SPLASHSCREEN_TIMEOUT);
        } else {
            goToActivity(LogActivity.class, SPLASHSCREEN_TIMEOUT);
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
        ImageView imageViewLogo = (ImageView) findViewById(R.id.splashscreen_imageview_logo);
        imageViewLogo.setImageDrawable(getResources().getDrawable(R.drawable.ic_app_logo));
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