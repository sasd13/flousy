package com.diderot.android.flousy;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;

import flousy.util.GridBox;
import flousy.util.GridItemBox;
import flousy.util.GridBoxAdapter;

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
        viewStub.setLayoutResource(R.layout.layout_gridbox);
        GridView gridMenu = (GridView) viewStub.inflate();

        final GridBox gridBox = new GridBox(R.layout.layout_griditembox);
        GridItemBox gridItemBox = null;
        for(int i=0; i<6; i++) {
            switch(i) {
                case 0:
                    gridItemBox = new GridItemBox(getString(R.string.activity_new_name));
                    gridItemBox.setBackgroundColor(NewActivity.ACTIVITY_COLOR);
                    gridItemBox.setImageResource(R.drawable.griditembox_new);
                    gridItemBox.setIntent(new Intent(this, NewActivity.class));
                    break;
                case 1:
                    gridItemBox = new GridItemBox(getString(R.string.activity_consult_name));
                    gridItemBox.setBackgroundColor(ConsultActivity.ACTIVITY_COLOR);
                    gridItemBox.setImageResource(R.drawable.griditembox_consult);
                    gridItemBox.setIntent(new Intent(this, ConsultActivity.class));
                    break;
                case 2:
                    gridItemBox = new GridItemBox(getString(R.string.activity_finances_name));
                    gridItemBox.setBackgroundColor(FinancesActivity.ACTIVITY_COLOR);
                    gridItemBox.setImageResource(R.drawable.griditembox_finances);
                    gridItemBox.setIntent(new Intent(this, FinancesActivity.class));
                    break;
                case 3:
                    gridItemBox = new GridItemBox(getString(R.string.activity_friends_name));
                    gridItemBox.setBackgroundColor(FriendsActivity.ACTIVITY_COLOR);
                    gridItemBox.setImageResource(R.drawable.griditembox_friends);
                    gridItemBox.setIntent(new Intent(this, FriendsActivity.class));
                    break;
                case 4:
                    gridItemBox = new GridItemBox(getString(R.string.activity_offers_name));
                    gridItemBox.setBackgroundColor(OffersActivity.ACTIVITY_COLOR);
                    gridItemBox.setImageResource(R.drawable.griditembox_offers);
                    gridItemBox.setIntent(new Intent(this, OffersActivity.class));
                    break;
                case 5:
                    gridItemBox = new GridItemBox(getString(R.string.activity_settings_name));
                    gridItemBox.setBackgroundColor(SettingsActivity.ACTIVITY_COLOR);
                    gridItemBox.setImageResource(R.drawable.griditembox_settings);
                    gridItemBox.setIntent(new Intent(this, SettingsActivity.class));
                    break;
            }
            gridBox.addGridItemBox(gridItemBox);
        }

        GridBoxAdapter gridBoxAdapter = new GridBoxAdapter(this, gridBox);
        gridMenu.setAdapter(gridBoxAdapter);

        gridMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(gridBox.getGridItemBoxes().get(position).getIntent());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_menu, menu);

        MenuItem item = (MenuItem) menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.widget.SearchView searchView = new android.widget.SearchView(this);
            item.setActionView(searchView);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        } else {
            SearchView searchView = new SearchView(this);
            MenuItemCompat.setActionView(item, searchView);
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