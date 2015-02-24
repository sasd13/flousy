package com.diderot.android.flousy;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SplashScreenActivity extends MyActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getIntent().addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);

        //Set activity color before everything
        setActivityColor(DEFAULT_ACTIVITY_COLOR);

        //Disable ActionBar
        setCustomActionBarEnabled(false);

        //Set ActivityBar
        setActivityBar(R.layout.layout_activitybar);

        //Set ActivityContent
        ViewStub stub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        stub.setLayoutResource(R.layout.layout_splashscreen);
        RelativeLayout layoutSplashScreen = (RelativeLayout) stub.inflate();

        this.runnable = new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, MenuActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        };

        this.handler = new Handler();
        this.handler.postDelayed(this.runnable, SPLASH_TIME_OUT);
    }

    /**
     * Stop calling post delayed activity on user action
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        this.handler.removeCallbacks(this.runnable);
        finish();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        this.handler.removeCallbacks(this.runnable);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_myactivity, menu);

        MenuItem item = menu.add(R.string.action_search);
        item.setIcon(R.drawable.ic_action_search);

        if (Build.VERSION.SDK_INT >= 11) {
            SearchView searchView = new SearchView(this);
            item.setActionView(new SearchView(this));
        }

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
}