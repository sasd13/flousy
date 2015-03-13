package com.diderot.android.flousy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import flousy.util.activitybar.ActivityBarFactory;
import flousy.util.activitybar.SimpleActivityBar;
import flousy.util.color.CustomColor;

public class ConsultActivity extends MyActivity {

    public static final int ACTIVITY_COLOR = R.color.customRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        CustomColor activityColor = new CustomColor(getResources().getColor(ACTIVITY_COLOR));
        setActivityColor(activityColor);

        //Set ActivityBar
        ActivityBarFactory factory = new ActivityBarFactory();
        SimpleActivityBar activityBar = (SimpleActivityBar) factory.createActivityBar(ActivityBarFactory.TYPE_SIMPLEACTIVITYBAR);
        setActivityBar(activityBar);
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
}