package com.diderot.android.flousy;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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

import flousy.util.activitybar.ActivityBarFactory;
import flousy.util.activitybar.SimpleActivityBar;
import flousy.util.color.CustomColor;
import flousy.util.widget.GridBox;
import flousy.util.widget.GridItemBox;
import flousy.util.widget.GridBoxAdapter;

public class MenuActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        CustomColor activityColor = new CustomColor(getResources().getColor(APP_COLOR));
        setActivityColor(activityColor);

        //Disable previous remote
        setActionBarDisplayHomeAsUpEnabled(false);

        //Set ActivityBar
        ActivityBarFactory factory = new ActivityBarFactory();
        SimpleActivityBar activityBar = (SimpleActivityBar) factory.createActivityBar(ActivityBarFactory.TYPE_SIMPLEACTIVITYBAR);
        setActivityBar(activityBar);

        //Set ActivityContent
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(R.layout.layout_gridbox);
        GridView gridMenu = (GridView) viewStub.inflate();

        GridBox gridBox = new GridBox(R.layout.layout_griditembox);
        GridItemBox gridItemBox;
        CustomColor gridItemBoxColor;

        for(int i=0; i<6; i++) {
            gridItemBox = new GridItemBox();
            gridItemBoxColor = new CustomColor();

            switch(i) {
                case 0:
                    gridItemBoxColor.setColor(getResources().getColor(NewActivity.ACTIVITY_COLOR));
                    gridItemBox.setText(getResources().getString(R.string.activity_new_name));
                    gridItemBox.setImage(getResources().getDrawable(R.drawable.griditembox_new));
                    gridItemBox.setIntent(new Intent(this, NewActivity.class));
                    break;
                case 1:
                    gridItemBoxColor.setColor(getResources().getColor(ConsultActivity.ACTIVITY_COLOR));
                    gridItemBox.setText(getResources().getString(R.string.activity_consult_name));
                    gridItemBox.setImage(getResources().getDrawable(R.drawable.griditembox_consult));
                    gridItemBox.setIntent(new Intent(this, ConsultActivity.class));
                    break;
                case 2:
                    gridItemBoxColor.setColor(getResources().getColor(FinancesActivity.ACTIVITY_COLOR));
                    gridItemBox.setText(getResources().getString(R.string.activity_finances_name));
                    gridItemBox.setImage(getResources().getDrawable(R.drawable.griditembox_finances));
                    gridItemBox.setIntent(new Intent(this, FinancesActivity.class));
                    break;
                case 3:
                    gridItemBoxColor.setColor(getResources().getColor(FriendsActivity.ACTIVITY_COLOR));
                    gridItemBox.setText(getResources().getString(R.string.activity_friends_name));
                    gridItemBox.setImage(getResources().getDrawable(R.drawable.griditembox_friends));
                    gridItemBox.setIntent(new Intent(this, FriendsActivity.class));
                    break;
                case 4:
                    gridItemBoxColor.setColor(getResources().getColor(OffersActivity.ACTIVITY_COLOR));
                    gridItemBox.setText(getResources().getString(R.string.activity_offers_name));
                    gridItemBox.setImage(getResources().getDrawable(R.drawable.griditembox_offers));
                    gridItemBox.setIntent(new Intent(this, OffersActivity.class));
                    break;
                case 5:
                    gridItemBoxColor.setColor(getResources().getColor(SettingsActivity.ACTIVITY_COLOR));
                    gridItemBox.setText(getResources().getString(R.string.activity_settings_name));
                    gridItemBox.setImage(getResources().getDrawable(R.drawable.griditembox_settings));
                    gridItemBox.setIntent(new Intent(this, SettingsActivity.class));
                    break;
            }

            gridItemBox.setColor(gridItemBoxColor);
            gridBox.addGridItemBox(gridItemBox);
        }

        GridBoxAdapter gridBoxAdapter = new GridBoxAdapter(this, gridBox);
        gridMenu.setAdapter(gridBoxAdapter);
        gridMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GridItemBox gridItemBox = (GridItemBox) parent.getAdapter().getItem(position);
                startActivity(gridItemBox.getIntent());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent().hasExtra("EXIT") && getIntent().getBooleanExtra("EXIT", false) == true) {
            Intent loginActivity = new Intent(this, LogInActivity.class);
            startActivity(loginActivity);
            finish();
        }
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