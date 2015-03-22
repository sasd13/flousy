package com.diderot.android.flousy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import flousy.gui.actionbar.ActionBarType;
import flousy.gui.actionbar.BaseActionBar;
import flousy.gui.activitybar.ActivityBarType;
import flousy.gui.activitybar.TitledActivityBar;

public class FriendsActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customBlue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        setActivityColor(getResources().getColor(ACTIVITY_COLOR));

        //Set ActionBar
        BaseActionBar actionBar = (BaseActionBar) createActionBar(ActionBarType.BASEBAR);
        actionBar.getTextViewTitle().setText(R.string.activity_friends_name);

        ImageButton buttonRefresh = actionBar.getImageButtonActionFirst();
        buttonRefresh.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_refresh));

        //Set ActivityBar
        TitledActivityBar activityBar = (TitledActivityBar) createActivityBar(ActivityBarType.TITLEDBAR);
        activityBar.setTitle(getResources().getString(R.string.friends_tabedbar_tab_received));
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