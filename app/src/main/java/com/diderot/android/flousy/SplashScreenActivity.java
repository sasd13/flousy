package com.diderot.android.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import flousy.user.SessionManager;

public class SplashScreenActivity extends MyActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private Handler handler;
    private Runnable runnable;

    private static final int APP_LOGO = R.drawable.ic_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        setActivityColor(DEFAULT_ACTIVITY_COLOR);

        //Disable ActionBar
        setActionBarEnabled(false);

        //Set ActivityContent
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(R.layout.activity_splashscreen);
        RelativeLayout splash = (RelativeLayout) viewStub.inflate();

        //Set Logo
        ImageView imageView = (ImageView) findViewById(R.id.splashscreen_logo);
        imageView.setImageResource(APP_LOGO);
    }

    @Override
    protected void onStart() {
        super.onStart();

        SessionManager session = SessionManager.getInstance(this);
        String login = session.checkUserLogin();

        if(login == null) {
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
        int id = item.getItemId();

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
        if(this.runnable != null) {
            this.handler.removeCallbacks(this.runnable);
        }
    }
}