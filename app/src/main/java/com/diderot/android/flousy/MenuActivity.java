package com.diderot.android.flousy;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
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
        setCustomActionBarDisplayHomeAsUpEnabled(false);

        //Set ActivityBar
        setActivityBar(R.layout.layout_activitybar);

        //Set ActivityContent
        ViewStub stub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        stub.setLayoutResource(R.layout.layout_menubox);
        GridView gridMenu = (GridView) stub.inflate();

        final MenuBox menuBox = new MenuBox("Menu", R.layout.layout_menuitembox);
        MenuItemBox menuItemBox = null;
        for(int i=0; i<6; i++) {
            switch(i) {
                case 0:
                    menuItemBox = new MenuItemBox(getString(R.string.activity_newactivity_name));
                    menuItemBox.setBackgroundColor(R.color.customGreen);
                    menuItemBox.setIntent(new Intent(getApplicationContext(), NewActivity.class));
                    break;
                case 1:
                    menuItemBox = new MenuItemBox(getString(R.string.activity_consultactivity_name));
                    menuItemBox.setBackgroundColor(R.color.customRed);
                    menuItemBox.setIntent(new Intent(getApplicationContext(), ConsultActivity.class));
                    break;
                case 2:
                    menuItemBox = new MenuItemBox(getString(R.string.activity_financesactivity_name));
                    menuItemBox.setBackgroundColor(R.color.customOrange);
                    menuItemBox.setIntent(new Intent(getApplicationContext(), FinancesActivity.class));
                    break;
                case 3:
                    menuItemBox = new MenuItemBox(getString(R.string.activity_friendsactivity_name));
                    menuItemBox.setBackgroundColor(R.color.customBlue);
                    menuItemBox.setIntent(new Intent(getApplicationContext(), FriendsActivity.class));
                    break;
                case 4:
                    menuItemBox = new MenuItemBox(getString(R.string.activity_offersactivity_name));
                    menuItemBox.setBackgroundColor(R.color.customYellow);
                    menuItemBox.setIntent(new Intent(getApplicationContext(), OffersActivity.class));
                    break;
                case 5:
                    menuItemBox = new MenuItemBox(getString(R.string.activity_settingsactivity_name));
                    menuItemBox.setBackgroundColor(R.color.customPurple);
                    menuItemBox.setIntent(new Intent(getApplicationContext(), SettingsActivity.class));
                    break;
            }
            menuBox.addMenuItemBox(menuItemBox);
        }

        gridMenu.setAdapter(new MenuBoxAdapter(getApplicationContext(), menuBox));

        gridMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(menuBox.getMenuItemBoxes().get(position).getIntent());
            }
        });
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