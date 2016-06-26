package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.sasd13.androidex.util.Session;
import com.sasd13.androidex.util.TaskInitializer;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.gui.browser.Browser;
import com.sasd13.flousy.util.SessionHelper;

public class SplashScreenActivity extends AppCompatActivity implements TaskInitializer.Loader {

    private static final int TIMEOUT = 3000;

    private TaskPlanner taskPlanner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);
        init();
    }

    private void init() {
        TaskInitializer taskInitializer = new TaskInitializer(this);
        taskInitializer.execute();

        Session.init(this);

        if (SessionHelper.isLogged()) {
            goToActivity(HomeActivity.class);
        } else {
            goToActivity(LoginActivity.class);
        }
    }

    @Override
    public void load() {
        SQLiteDAO.getInstance().init(this);
        Browser.getInstance().init(this);
        createLogo();
    }

    @Override
    public void run() {

    }

    private void createLogo() {
        ImageView imageViewLogo = (ImageView) findViewById(R.id.splashscreen_imageview);
        imageViewLogo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_app_logo));
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