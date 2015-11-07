package com.example.flousy;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;

import flousy.gui.content.HomeMenuItem;
import flousy.gui.content.ListHomeMenuItems;
import flousy.gui.widget.recycler.drawer.DrawerItemHomeMenu;
import flousy.gui.widget.recycler.drawer.Drawer;
import flousy.gui.widget.recycler.drawer.DrawerItemTitle;

public abstract class MotherActivity extends ActionBarActivity {

    public static final int APP_COLOR = R.color.customGreenApp;

    private int color;
    private Drawer drawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        super.setContentView(R.layout.activity_mother);

        //Create Color with app color
        this.color = getResources().getColor(APP_COLOR);

        createDrawer();
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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setContentView(int layoutResource) {
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(layoutResource);
        viewStub.inflate();
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

    private void createDrawer() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.drawer = new Drawer(this, drawerLayout);

        RecyclerView drawerView = (RecyclerView) findViewById(R.id.drawer_view);
        this.drawer.adapt(drawerView);

        DrawerItemTitle drawerItemTitle = new DrawerItemTitle();
        drawerItemTitle.setText(getResources().getString(R.string.activity_home_name));
        this.drawer.addItem(drawerItemTitle);

        ListHomeMenuItems listHomeMenuItems = ListHomeMenuItems.getInstance(this);

        DrawerItemHomeMenu drawerItemHomeMenu;
        for(HomeMenuItem homeMenuItem : listHomeMenuItems) {
            drawerItemHomeMenu = new DrawerItemHomeMenu();

            drawerItemHomeMenu.setColor(homeMenuItem.getColor());
            drawerItemHomeMenu.setText(homeMenuItem.getName());
            drawerItemHomeMenu.setIntent(homeMenuItem.getIntent());

            this.drawer.addItem(drawerItemHomeMenu);
        }
    }

    public void setActionBarEnabled(boolean enabled) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            try {
                if (enabled) {
                    getActionBar().show();
                } else {
                    getActionBar().hide();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            if (enabled) {
                getSupportActionBar().show();
            } else {
                getSupportActionBar().hide();
            }
        }
    }
}