package com.diderot.android.flousy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import flousy.gui.actionbar.ActionBar;
import flousy.gui.actionbar.ActionBarCustomizer;
import flousy.gui.drawer.Drawer;
import flousy.gui.drawer.DrawerItem;
import flousy.gui.drawer.DrawerItemTitle;

public class NewActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customGreen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        setActivityColor(getResources().getColor(ACTIVITY_COLOR));

        //Set CustomActionBar
        ActionBar actionBar = getCustomActionBar();
        ActionBarCustomizer.customize(this, actionBar);
        actionBar.getTitleView().setText(R.string.activity_new_name);
        actionBar.getSubTitleView().setText(R.string.new_actionbar_textview_subtitle);

        ImageButton buttonSearch = actionBar.getActionFirstButton();
        buttonSearch.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_search));

        ImageButton buttonAdd = actionBar.getActionSecondButton();
        buttonAdd.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_new));

        //Set Drawer
        Drawer drawer = getDrawer();
        DrawerItemTitle drawerItemTitle = new DrawerItemTitle("Catégorie");
        drawer.addItem(drawerItemTitle);

        DrawerItem drawerItem = new DrawerItem("Ajouter catégorie", null);
        drawer.addItem(drawerItem);
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