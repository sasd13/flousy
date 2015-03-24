package com.diderot.android.flousy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import flousy.gui.actionbar.ActionBarType;
import flousy.gui.actionbar.BaseActionBar;
import flousy.gui.drawer.Drawer;
import flousy.gui.drawer.BaseDrawerItem;
import flousy.gui.drawer.BaseDrawerItemTitle;

public class NewActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customGreen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        setActivityColor(getResources().getColor(ACTIVITY_COLOR));

        //Set NavDrawer
        Drawer drawer = getDrawer();
        BaseDrawerItemTitle baseDrawerItemTitle = new BaseDrawerItemTitle(this, "Catégorie");
        drawer.addItem(baseDrawerItemTitle);

        BaseDrawerItem baseDrawerItem = new BaseDrawerItem(this, "Ajouter catégorie", null);
        drawer.addItem(baseDrawerItem);

        //Set ActionBar
        BaseActionBar actionBar = (BaseActionBar) createActionBar(ActionBarType.BASEBAR);
        actionBar.getTextViewTitle().setText(R.string.activity_new_name);
        actionBar.setSubTitleEnabled(true);
        actionBar.getTextViewSubTitle().setText(R.string.new_actionbar_textview_subtitle);

        ImageButton buttonSearch = actionBar.getImageButtonActionFirst();
        buttonSearch.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_search));

        ImageButton buttonAdd = actionBar.getImageButtonActionSecond();
        buttonAdd.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_new));
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