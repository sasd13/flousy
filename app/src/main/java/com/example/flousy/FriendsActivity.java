package com.example.flousy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import flousy.gui.activitybar.ActivityBarType;
import flousy.gui.activitybar.TitledActivityBar;

public class FriendsActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customBlue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set ActivityColor immediately after content view
        setColor(getResources().getColor(ACTIVITY_COLOR));

        //Set ActivityBar
        TitledActivityBar activityBar = (TitledActivityBar) createActivityBar(ActivityBarType.TITLEDBAR);
        activityBar.setTitle(getResources().getString(R.string.friends_activitybar_tabed_tab_reception));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}