package com.diderot.android.flousy;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.GridView;

import flousy.util.MenuBox;
import flousy.util.MenuItemBox;
import flousy.util.MenuBoxAdapter;

public class MenuActivity extends MyActivity {

    public static final int ACTIVITY_COLOR = DEFAULT_ACTIVITY_COLOR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        setActivityColor(ACTIVITY_COLOR);

        //Disable previous remote
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

        //Set LineBar
        setActivityLineBar(R.layout.layout_bar);

        //Set content
        ViewStub stub = (ViewStub) findViewById(R.id.viewStub_layoutContent);
        stub.setLayoutResource(R.layout.layout_gridmenu);
        GridView gridMenu = (GridView) stub.inflate();

        MenuBox menuBox = new MenuBox("Menu");
        MenuItemBox menuItemBox;
        for(int i=0; i<6; i++) {
            menuItemBox = new MenuItemBox();
            switch(i) {
                case 0:
                    menuItemBox.setTextValue("Nouveau");
                    menuItemBox.setBackgroundColor(R.color.customGreen);
                    break;
                case 1:
                    menuItemBox.setTextValue("Consulter");
                    menuItemBox.setBackgroundColor(R.color.customRed);
                    break;
                case 2:
                    menuItemBox.setTextValue("Finances");
                    menuItemBox.setBackgroundColor(R.color.customOrange);
                    break;
                case 3:
                    menuItemBox.setTextValue("Amis");
                    menuItemBox.setBackgroundColor(R.color.customBlue);
                    break;
                case 4:
                    menuItemBox.setTextValue("Offres");
                    menuItemBox.setBackgroundColor(R.color.customYellow);
                    break;
                case 5:
                    menuItemBox.setTextValue("Paramètres");
                    menuItemBox.setBackgroundColor(R.color.customPurple);
                    break;
            }
            menuBox.addMenuItemBox(menuItemBox);
        }

        gridMenu.setAdapter(new MenuBoxAdapter(getApplicationContext(), menuBox));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_myactivity, menu);

        MenuItem item = menu.add(R.string.action_search);
        item.setIcon(R.drawable.ic_action_search);

        if (Build.VERSION.SDK_INT >= 11) {
            SearchView searchView = new SearchView(this);
            item.setActionView(new SearchView(this));
        }

        super.onCreateOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
