package com.sasd13.flousy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.sasd13.androidex.db.ISQLiteDAO;
import com.sasd13.androidex.session.Session;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.db.DAOFactory;

public class SplashScreenActivity extends Activity {

    private static final int TIMEOUT = 3000;

    private TaskPlanner taskPlanner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);
        createLogo();
    }

    private void createLogo() {
        ImageView imageViewLogo = (ImageView) findViewById(R.id.splashscreen_imageview);
        imageViewLogo.setImageDrawable(getResources().getDrawable(R.drawable.ic_app_logo));
    }

    @Override
    protected void onStart() {
        super.onStart();

        ((ISQLiteDAO) DAOFactory.make()).init(this);
        Session.init(this);
        if (Session.isStarted()) {
            goToActivity(HomeActivity.class);
        } else {
            goToActivity(LogActivity.class);
        }
    }

    private void goToActivity(final Class<?> mClass) {
        taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, mClass);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        }, TIMEOUT);

        taskPlanner.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        stopGoToActivity();
    }

    private void stopGoToActivity() {
        try {
            taskPlanner.stop();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        stopGoToActivity();
    }
}