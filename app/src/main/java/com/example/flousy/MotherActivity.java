package com.example.flousy;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;

import flousy.gui.activitybar.ActivityBar;
import flousy.gui.activitybar.ActivityBarException;
import flousy.gui.activitybar.ActivityBarFactory;
import flousy.gui.activitybar.ActivityBarType;
import flousy.gui.content.FlousyMenu;
import flousy.gui.content.ListFlousyMenus;
import flousy.gui.widget.recycler.drawer.DrawerItemMenu;
import flousy.gui.widget.recycler.drawer.Drawer;
import flousy.gui.widget.recycler.drawer.DrawerItemTitle;

public class MotherActivity extends ActionBarActivity {

    public static final int APP_COLOR = R.color.customGreenApp;

    private int color;
    private Drawer drawer;
    private ActivityBar activityBar;
    private View contentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        super.setContentView(R.layout.activity_mother);

        //Create Color with app color
        this.color = getResources().getColor(APP_COLOR);

        //Create Drawer
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.drawer = new Drawer(this, drawerLayout);

        RecyclerView drawerView = (RecyclerView) findViewById(R.id.drawer_view);
        this.drawer.adapt(drawerView);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (this.drawer.isOpened()) {
            this.drawer.setOpened(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setContentView(int layoutResource) {
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(layoutResource);
        this.contentView = viewStub.inflate();
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Drawer getDrawer() {
        return this.drawer;
    }

    public ActivityBar getActivityBar() {
        return this.activityBar;
    }

    public ActivityBar createActivityBar(ActivityBarType type) {
        try {
            this.activityBar = ActivityBarFactory.create(type);
            this.activityBar.inflate((ViewStub) findViewById(R.id.activitybar_viewstub));
            this.activityBar.setColor(this.color);
        } catch (ActivityBarException e) {
            e.printStackTrace();
        }

        return this.activityBar;
    }

    private void addDrawerItems() {
        DrawerItemTitle drawerItemTitle = new DrawerItemTitle();
        drawerItemTitle.setText(getResources().getString(R.string.activity_menu_name));
        this.drawer.addItem(drawerItemTitle);

        ListFlousyMenus listFlousyMenus = ListFlousyMenus.getInstance(this);

        DrawerItemMenu drawerItemMenu;
        for(Object flousyMenu : listFlousyMenus) {
            drawerItemMenu = new DrawerItemMenu();

            drawerItemMenu.setColor(((FlousyMenu) flousyMenu).getColor());
            drawerItemMenu.setText(((FlousyMenu) flousyMenu).getName());
            drawerItemMenu.setIntent(((FlousyMenu) flousyMenu).getIntent());

            this.drawer.addItem(drawerItemMenu);
        }
    }
}
