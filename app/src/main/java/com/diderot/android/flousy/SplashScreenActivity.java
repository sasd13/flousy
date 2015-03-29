package com.diderot.android.flousy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import flousy.util.SessionManager;

public class SplashScreenActivity extends Activity {

    private static int SPLASH_TIME_OUT = 3000;
    private Handler handler;
    private Runnable runnable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        setContentView(R.layout.activity_splashscreen_layout);

        //Set Logo
        ImageView imageView = (ImageView) findViewById(R.id.splashscreen_imageview_logo);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_app_logo));
    }

    @Override
    protected void onStart() {
        super.onStart();

        SessionManager session = new SessionManager(this);
        boolean logged = session.checkUserEmail();

        if(logged == false) {
            attachActivity(LogInActivity.class, SPLASH_TIME_OUT);
        } else {
            attachActivity(MenuActivity.class, SPLASH_TIME_OUT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
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
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

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
        if(this.runnable != null && this.handler != null) {
            this.handler.removeCallbacks(this.runnable);
        }
    }
}