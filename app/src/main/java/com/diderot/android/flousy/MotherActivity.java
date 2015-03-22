package com.diderot.android.flousy;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.ListView;

import flousy.gui.actionbar.AbstractActionBar;
import flousy.gui.actionbar.ActionBarCustomizer;
import flousy.gui.actionbar.ActionBarFactory;
import flousy.gui.actionbar.ActionBarType;
import flousy.gui.activitybar.ActivityBar;
import flousy.gui.activitybar.ActivityBarFactory;
import flousy.gui.activitybar.ActivityBarType;
import flousy.gui.activitycontent.ActivityContentCustomizer;
import flousy.gui.color.CustomColor;
import flousy.gui.app.NativeActionBarManager;
import flousy.gui.navdrawer.ListNavDrawerItem;
import flousy.gui.navdrawer.MenuNavDrawerItem;
import flousy.gui.navdrawer.NavDrawerAdapter;
import flousy.gui.navdrawer.NavDrawerItemGroup;

public class MotherActivity extends ActionBarActivity {

    public static final int APP_COLOR = R.color.customGreenApp;

    private int activityColor;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private String[] menuTitles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        setContentView(R.layout.activity_mother);

        //Disable native
        NativeActionBarManager.setActionBarEnabled(this, false);

        //Create activity color with app color
        this.activityColor = getResources().getColor(APP_COLOR);

        //Navigation drawer
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.drawerList = (ListView) findViewById(R.id.right_drawer);
        this.menuTitles = getResources().getStringArray(R.array.array_menu);

        createNavDrawer();
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

    public AbstractActionBar createActionBar(ActionBarType type) {
        AbstractActionBar actionBar = ActionBarFactory.create(this, type);

        if(actionBar != null) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.actionbar_viewstub);
            actionBar.inflate(viewStub);
            ActionBarCustomizer.customize(actionBar, this);
        }

        return actionBar;
    }

    public void createNavDrawer() {
        ListNavDrawerItem listNavDrawerItem = new ListNavDrawerItem();

        NavDrawerItemGroup navDrawerItemGroup;

        navDrawerItemGroup = new NavDrawerItemGroup(this, R.layout.navdraweritemgroup_base, "Menu".toUpperCase());
        MenuNavDrawerItem menuNavDrawerItem = null;
        Resources resources = getResources();
        for(int i=0; i<6; i++) {
            switch (i) {
                case 0:
                    menuNavDrawerItem = new MenuNavDrawerItem(
                            this,
                            resources.getString(R.string.activity_new_name),
                            new Intent(this, NewActivity.class),
                            resources.getColor(NewActivity.ACTIVITY_COLOR));
                    break;
                case 1:
                    menuNavDrawerItem = new MenuNavDrawerItem(
                            this,
                            resources.getString(R.string.activity_consult_name),
                            new Intent(this, ConsultActivity.class),
                            resources.getColor(ConsultActivity.ACTIVITY_COLOR));
                    break;
                case 2:
                    menuNavDrawerItem = new MenuNavDrawerItem(
                            this,
                            resources.getString(R.string.activity_finances_name),
                            new Intent(this, FinancesActivity.class),
                            resources.getColor(FinancesActivity.ACTIVITY_COLOR));
                    break;
                case 3:
                    menuNavDrawerItem = new MenuNavDrawerItem(
                            this,
                            resources.getString(R.string.activity_friends_name),
                            new Intent(this, FriendsActivity.class),
                            resources.getColor(FriendsActivity.ACTIVITY_COLOR));
                    break;
                case 4:
                    menuNavDrawerItem = new MenuNavDrawerItem(
                            this,
                            resources.getString(R.string.activity_offers_name),
                            new Intent(this, OffersActivity.class),
                            resources.getColor(OffersActivity.ACTIVITY_COLOR));
                    break;
                case 5:
                    menuNavDrawerItem = new MenuNavDrawerItem(
                            this,
                            resources.getString(R.string.activity_settings_name),
                            new Intent(this, SettingsActivity.class),
                            resources.getColor(SettingsActivity.ACTIVITY_COLOR));
                    break;
            }
            navDrawerItemGroup.addChild(menuNavDrawerItem);
        }

        listNavDrawerItem.add(navDrawerItemGroup);
        this.drawerList.setAdapter(new NavDrawerAdapter(listNavDrawerItem));
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
        View view = null;

        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(layoutResource);
        view = viewStub.inflate();
        ActivityContentCustomizer.customizeColor(view, this);

        return view;
    }
}
