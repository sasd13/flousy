package com.diderot.android.flousy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import flousy.gui.actionbar.ActionBar;
import flousy.gui.drawer.Drawer;

public class OffersActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customPurple;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        setActivityColor(getResources().getColor(ACTIVITY_COLOR));

        //Set CustomActionBar
        ActionBar actionBar = getCustomActionBar().setNavigationUp(this);
        actionBar.getTitleView().setText(R.string.activity_offers_name);

        //Set Drawer
        Drawer drawer = getDrawer();

        //Customize activity
        customizeColor(getActivityColor());
        customizeText();
        customizeDimensions();
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
}