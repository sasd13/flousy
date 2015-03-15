package com.diderot.android.flousy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import flousy.gui.activitybar.ActivityBarType;
import flousy.gui.activitybar.TitledActivityBar;
import flousy.gui.color.CustomColor;

public class NewActivity extends MyActivity {

    public static final int ACTIVITY_COLOR = R.color.customGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        CustomColor activityColor = new CustomColor(getResources().getColor(ACTIVITY_COLOR));
        setActivityColor(activityColor);

        //Set ActivityBar
        TitledActivityBar activityBar = (TitledActivityBar) createActivityBar(ActivityBarType.TITLEDBAR);
        activityBar.setTitle(getResources().getString(R.string.new_titledbar_title));
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
        switch (item.getItemId()) {
            default :
                return super.onOptionsItemSelected(item);
        }
    }
}