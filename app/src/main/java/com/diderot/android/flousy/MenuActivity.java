package com.diderot.android.flousy;

import android.content.Intent;
import android.os.Build;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        setActivityColor(DEFAULT_ACTIVITY_COLOR);

        //Disable previous remote
        setActionBarDisplayHomeAsUpEnabled(false);

        //Set ActivityBar
        setActivityBar(R.layout.layout_activitybar);

        //Set ActivityContent
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(R.layout.layout_menubox);
        GridView gridMenu = (GridView) viewStub.inflate();

        final MenuBox menuBox = new MenuBox(R.layout.layout_menuitembox);
        MenuItemBox menuItemBox = null;
        for(int i=0; i<6; i++) {
            switch(i) {
                case 0:
                    menuItemBox = new MenuItemBox(getString(R.string.activity_new_name));
                    menuItemBox.setBackgroundColor(NewActivity.ACTIVITY_COLOR);
                    menuItemBox.setImageResource(R.drawable.menuitembox_new);
                    menuItemBox.setIntent(new Intent(this, NewActivity.class));
                    break;
                case 1:
                    menuItemBox = new MenuItemBox(getString(R.string.activity_consult_name));
                    menuItemBox.setBackgroundColor(ConsultActivity.ACTIVITY_COLOR);
                    menuItemBox.setImageResource(R.drawable.menuitembox_consult);
                    menuItemBox.setIntent(new Intent(this, ConsultActivity.class));
                    break;
                case 2:
                    menuItemBox = new MenuItemBox(getString(R.string.activity_finances_name));
                    menuItemBox.setBackgroundColor(FinancesActivity.ACTIVITY_COLOR);
                    menuItemBox.setImageResource(R.drawable.menuitembox_finances);
                    menuItemBox.setIntent(new Intent(this, FinancesActivity.class));
                    break;
                case 3:
                    menuItemBox = new MenuItemBox(getString(R.string.activity_friends_name));
                    menuItemBox.setBackgroundColor(FriendsActivity.ACTIVITY_COLOR);
                    menuItemBox.setImageResource(R.drawable.menuitembox_friends);
                    menuItemBox.setIntent(new Intent(this, FriendsActivity.class));
                    break;
                case 4:
                    menuItemBox = new MenuItemBox(getString(R.string.activity_offers_name));
                    menuItemBox.setBackgroundColor(OffersActivity.ACTIVITY_COLOR);
                    menuItemBox.setImageResource(R.drawable.menuitembox_offers);
                    menuItemBox.setIntent(new Intent(this, OffersActivity.class));
                    break;
                case 5:
                    menuItemBox = new MenuItemBox(getString(R.string.activity_settings_name));
                    menuItemBox.setBackgroundColor(SettingsActivity.ACTIVITY_COLOR);
                    menuItemBox.setImageResource(R.drawable.menuitembox_settings);
                    menuItemBox.setIntent(new Intent(this, SettingsActivity.class));
                    break;
            }
            menuBox.addMenuItemBox(menuItemBox);
        }

        MenuBoxAdapter menuBoxAdapter = new MenuBoxAdapter(this, menuBox);
        gridMenu.setAdapter(menuBoxAdapter);

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