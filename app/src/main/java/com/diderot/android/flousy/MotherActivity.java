package com.diderot.android.flousy;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
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
import flousy.gui.navdrawer.BaseNavDrawer;
import flousy.gui.navdrawer.MenuNavDrawerItem;
import flousy.gui.navdrawer.NavDrawer;
import flousy.gui.navdrawer.BaseNavDrawerItemGroup;
import flousy.gui.navdrawer.NavDrawerFactory;
import flousy.gui.navdrawer.NavDrawerType;

public class MotherActivity extends ActionBarActivity {

    public static final int APP_COLOR = R.color.customGreenApp;

    private int activityColor;
    private NavDrawer navDrawer;
    private ListView drawerList;

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
        this.drawerList = (ListView) findViewById(R.id.right_drawer);
        this.navDrawer = NavDrawerFactory.create(this, NavDrawerType.BASEDRAWER);
        this.navDrawer.adapt(this.drawerList);

        BaseNavDrawerItemGroup baseNavDrawerItemGroup = new BaseNavDrawerItemGroup(this, getResources().getString(R.string.activity_menu_name));

        MenuNavDrawerItem menuNavDrawerItem = null;
        Resources resources = getResources();
        for(int i=0; i<6; i++) {
            switch (i) {
                case 0:
                    menuNavDrawerItem = new MenuNavDrawerItem(
                            resources.getString(R.string.activity_new_name),
                            new Intent(this, NewActivity.class),
                            resources.getColor(NewActivity.ACTIVITY_COLOR));
                    break;
                case 1:
                    menuNavDrawerItem = new MenuNavDrawerItem(
                            resources.getString(R.string.activity_consult_name),
                            new Intent(this, ConsultActivity.class),
                            resources.getColor(ConsultActivity.ACTIVITY_COLOR));
                    break;
                case 2:
                    menuNavDrawerItem = new MenuNavDrawerItem(
                            resources.getString(R.string.activity_finances_name),
                            new Intent(this, FinancesActivity.class),
                            resources.getColor(FinancesActivity.ACTIVITY_COLOR));
                    break;
                case 3:
                    menuNavDrawerItem = new MenuNavDrawerItem(
                            resources.getString(R.string.activity_friends_name),
                            new Intent(this, FriendsActivity.class),
                            resources.getColor(FriendsActivity.ACTIVITY_COLOR));
                    break;
                case 4:
                    menuNavDrawerItem = new MenuNavDrawerItem(
                            resources.getString(R.string.activity_offers_name),
                            new Intent(this, OffersActivity.class),
                            resources.getColor(OffersActivity.ACTIVITY_COLOR));
                    break;
                case 5:
                    menuNavDrawerItem = new MenuNavDrawerItem(
                            resources.getString(R.string.activity_settings_name),
                            new Intent(this, SettingsActivity.class),
                            resources.getColor(SettingsActivity.ACTIVITY_COLOR));
                    break;
            }
            baseNavDrawerItemGroup.addChild(menuNavDrawerItem);
        }
        this.navDrawer.addItem(baseNavDrawerItemGroup);
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

    public NavDrawer getNavDrawer() {
        return this.navDrawer;
    }

    public AbstractActionBar createActionBar(ActionBarType type) {
        AbstractActionBar actionBar = ActionBarFactory.create(type);

        if(actionBar != null) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.actionbar_viewstub);
            actionBar.inflate(viewStub);
            ActionBarCustomizer.customize(actionBar, this);
        }

        return actionBar;
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
        ActivityContentCustomizer.customize(this, view);

        return view;
    }
}
