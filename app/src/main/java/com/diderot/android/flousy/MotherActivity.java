package com.diderot.android.flousy;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import flousy.gui.actionbar.ActionBar;
import flousy.gui.activitybar.ActivityBar;
import flousy.gui.activitybar.ActivityBarFactory;
import flousy.gui.activitybar.ActivityBarType;
import flousy.gui.activitycontent.ActivityContentCustomizer;
import flousy.gui.color.CustomColor;
import flousy.gui.drawer.MenuDrawerItem;
import flousy.gui.drawer.Drawer;
import flousy.gui.drawer.DrawerItemTitle;

public class MotherActivity extends Activity {

    public static final int APP_COLOR = R.color.customGreenApp;

    private int activityColor;
    private ActionBar actionBar;
    private Drawer drawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        setContentView(R.layout.activity_mother);

        //Create activity color with app color
        this.activityColor = getResources().getColor(APP_COLOR);

        //Create CustomActionBar
        ViewStub actionbarStub = (ViewStub) findViewById(R.id.actionbar_viewstub);
        this.actionBar = new ActionBar();
        this.actionBar.inflate(actionbarStub);

        //Create Drawer
        RecyclerView drawerView = (RecyclerView) findViewById(R.id.drawer);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        drawerView.setHasFixedSize(true);

        // use a linear layout manager
        drawerView.setLayoutManager(new LinearLayoutManager(this));

        //set adapter
        this.drawer = new Drawer();
        this.drawer.adapt(drawerView);

        DrawerItemTitle drawerItemTitle = new DrawerItemTitle();
        drawerItemTitle.setTitle(getResources().getString(R.string.activity_menu_name));
        this.drawer.addItem(drawerItemTitle);

        MenuDrawerItem menuDrawerItem = null;
        Resources resources = getResources();
        for(int i=0; i<6; i++) {
            switch (i) {
                case 0:
                    menuDrawerItem = new MenuDrawerItem();
                    menuDrawerItem.setText(resources.getString(R.string.activity_new_name));
                    menuDrawerItem.setIntent(new Intent(this, NewActivity.class));
                    menuDrawerItem.setColor(resources.getColor(NewActivity.ACTIVITY_COLOR));
                    break;
                case 1:
                    menuDrawerItem = new MenuDrawerItem();
                    menuDrawerItem.setText(resources.getString(R.string.activity_consult_name));
                    menuDrawerItem.setIntent(new Intent(this, ConsultActivity.class));
                    menuDrawerItem.setColor(resources.getColor(ConsultActivity.ACTIVITY_COLOR));
                    break;
                case 2:
                    menuDrawerItem = new MenuDrawerItem();
                    menuDrawerItem.setText(resources.getString(R.string.activity_finances_name));
                    menuDrawerItem.setIntent(new Intent(this, FinancesActivity.class));
                    menuDrawerItem.setColor(resources.getColor(FinancesActivity.ACTIVITY_COLOR));
                    break;
                case 3:
                    menuDrawerItem = new MenuDrawerItem();
                    menuDrawerItem.setText(resources.getString(R.string.activity_friends_name));
                    menuDrawerItem.setIntent(new Intent(this, FriendsActivity.class));
                    menuDrawerItem.setColor(resources.getColor(FriendsActivity.ACTIVITY_COLOR));
                    break;
                case 4:
                    menuDrawerItem = new MenuDrawerItem();
                    menuDrawerItem.setText(resources.getString(R.string.activity_offers_name));
                    menuDrawerItem.setIntent(new Intent(this, OffersActivity.class));
                    menuDrawerItem.setColor(resources.getColor(OffersActivity.ACTIVITY_COLOR));
                    break;
                case 5:
                    menuDrawerItem = new MenuDrawerItem();
                    menuDrawerItem.setText(resources.getString(R.string.activity_settings_name));
                    menuDrawerItem.setIntent(new Intent(this, SettingsActivity.class));
                    menuDrawerItem.setColor(resources.getColor(SettingsActivity.ACTIVITY_COLOR));
                    break;
            }
            this.drawer.addItem(menuDrawerItem);
        }
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

        //Disable re-onCreate for subclasses on up button click
        return super.onOptionsItemSelected(item);
    }

    public int getActivityColor() {
        return this.activityColor;
    }

    public void setActivityColor(int activityColor) {
        this.activityColor = activityColor;
    }

    public ActionBar getCustomActionBar() {
        return this.actionBar;
    }

    public Drawer getDrawer() {
        return this.drawer;
    }

    public ActivityBar createActivityBar(ActivityBarType type) {
        ActivityBar activityBar = ActivityBarFactory.create(type);

        if(activityBar != null) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.activitybar_viewstub);
            activityBar.inflate(viewStub);
            activityBar.getView().setBackgroundColor(CustomColor.colorDarker(this.activityColor));
        }

        return activityBar;
    }

    public View createActivityContent(int layoutResource) {
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(layoutResource);
        View view = viewStub.inflate();

        if(view == null) {
            return null;
        }

        return view;
    }
}
