package com.diderot.android.flousy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import flousy.gui.actionbar.ActionBar;
import flousy.gui.activitybar.ActivityBarType;
import flousy.gui.activitybar.TitledActivityBar;

public class FriendsActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customBlue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set ActivityColor immediately after content view
        setActivityColor(getResources().getColor(ACTIVITY_COLOR));

        //Set CustomActionBar
        ActionBar actionBar = getCustomActionBar();
        actionBar.getTitleView().setText(R.string.activity_friends_name);

        ImageButton buttonRefresh = actionBar.getActionFirstButton();
        buttonRefresh.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_refresh));
        actionBar.setActionFirstButtonEnabled(true);

        //Set ActivityBar
        TitledActivityBar activityBar = (TitledActivityBar) createActivityBar(ActivityBarType.TITLEDBAR);
        activityBar.setTitle(getResources().getString(R.string.friends_activitybar_tabed_tab_reception));

        //Customize activity
        customizeColor();
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