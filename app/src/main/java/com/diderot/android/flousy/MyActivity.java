package com.diderot.android.flousy;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;

import flousy.util.activitybar.ActivityBar;
import flousy.util.color.CustomColor;

public class MyActivity extends ActionBarActivity {

    public static final int APP_COLOR = R.color.customGreenApp;

    private CustomColor activityColor;
    private ActivityBar activityBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        setContentView(R.layout.activity_my);

        //Create activity color with app color
        this.activityColor = new CustomColor(getResources().getColor(APP_COLOR));

        //Enable previous remote
        setActionBarDisplayHomeAsUpEnabled(true);
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

    public void setActionBarEnabled(boolean enabled) {
        if(enabled == true) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                try {
                    getActionBar().show();
                } catch (Exception e) {
                    Log.println(1, null, "ActionBar is null");
                }
            } else {
                getSupportActionBar().show();
            }
        } else {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                try {
                    getActionBar().hide();
                } catch (Exception e) {
                    Log.println(1, null, "ActionBar is null");
                }
            } else {
                getSupportActionBar().hide();
            }
        }
    }

    public void setActionBarDisplayHomeAsUpEnabled(boolean enabled) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            try {
                getActionBar().setDisplayHomeAsUpEnabled(enabled);
            } catch (Exception e) {
                Log.println(1, null, "ActionBar is null");
            }
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
        }
    }

    public CustomColor getActivityColor() {
        return this.activityColor;
    }

    public void setActivityColor(CustomColor activityColor) {
        this.activityColor = activityColor;
    }

    public ActivityBar getActivityBar() {
        return this.activityBar;
    }

    public void setActivityBar(ActivityBar activityBar) {
        this.activityBar = activityBar;

        ViewStub viewStub = (ViewStub) findViewById(R.id.activitybar_viewstub);
        View view = this.activityBar.create(viewStub);
        view.setBackgroundColor(this.activityColor.getColor());
    }
}
