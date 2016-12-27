package com.sasd13.flousy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.R;
import com.sasd13.flousy.util.SessionHelper;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int TIMEOUT = 2 * GUIConstants.TIMEOUT_ACTIVITY;

    private TaskPlanner taskPlanner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_splashscreen);
        buildView();
        run();
    }

    private void buildView() {
        ImageView imageViewLogo = (ImageView) findViewById(R.id.splashscreen_imageview);
        imageViewLogo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_app_logo));
    }

    public void run() {
        if (SessionHelper.isLogged(this)) {
            goToActivity(MainActivity.class);
        } else {
            goToActivity(SignActivity.class);
        }
    }

    private void goToActivity(final Class<? extends Activity> mClass) {
        taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, mClass));
            }
        }).start(TIMEOUT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        stopGoToActivity();
    }

    private void stopGoToActivity() {
        if (taskPlanner != null) {
            taskPlanner.stop();
        }
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        stopGoToActivity();
    }
}