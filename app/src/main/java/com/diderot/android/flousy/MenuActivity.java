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
import android.view.ViewStub;
import android.widget.GridView;

import flousy.util.activitybar.ActivityBarFactory;
import flousy.util.activitybar.SimpleActivityBar;
import flousy.util.color.CustomColor;
import flousy.util.grid.BaseGrid;
import flousy.util.grid.BaseGridItem;
import flousy.util.grid.GridFactory;
import flousy.util.grid.ListGridItem;

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
        ActivityBarFactory activityBarFactory = new ActivityBarFactory();
        SimpleActivityBar activityBar = (SimpleActivityBar) activityBarFactory.create(ActivityBarFactory.TYPE_SIMPLEACTIVITYBAR);
        setActivityBar(activityBar);

        //Set ActivityContent
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(R.layout.layout_basegrid);
        GridView gridView = (GridView) viewStub.inflate();

        GridFactory gridFactory = new GridFactory();
        BaseGrid menuGrid = (BaseGrid) gridFactory.create(this, GridFactory.TYPE_BASICGRID, gridView);

        ListGridItem listGridItem = new ListGridItem();

        BaseGridItem gridItem = null;
        Resources resources = getResources();

        for(int i=0; i<6; i++) {
            switch(i) {
                case 0:
                    gridItem = new BaseGridItem(
                            resources.getString(R.string.activity_new_name),
                            resources.getDrawable(R.drawable.griditembox_new),
                            new CustomColor(resources.getColor(NewActivity.ACTIVITY_COLOR)),
                            new Intent(this, NewActivity.class)
                    );
                    break;
                case 1:
                    gridItem = new BaseGridItem(
                            resources.getString(R.string.activity_consult_name),
                            resources.getDrawable(R.drawable.griditembox_consult),
                            new CustomColor(resources.getColor(ConsultActivity.ACTIVITY_COLOR)),
                            new Intent(this, ConsultActivity.class)
                    );
                    break;
                case 2:
                    gridItem = new BaseGridItem(
                            resources.getString(R.string.activity_finances_name),
                            resources.getDrawable(R.drawable.griditembox_finances),
                            new CustomColor(resources.getColor(FinancesActivity.ACTIVITY_COLOR)),
                            new Intent(this, FinancesActivity.class)
                    );
                    break;
                case 3:
                    gridItem = new BaseGridItem(
                            resources.getString(R.string.activity_friends_name),
                            resources.getDrawable(R.drawable.griditembox_friends),
                            new CustomColor(resources.getColor(FriendsActivity.ACTIVITY_COLOR)),
                            new Intent(this, FriendsActivity.class)
                    );
                    break;
                case 4:
                    gridItem = new BaseGridItem(
                            resources.getString(R.string.activity_offers_name),
                            resources.getDrawable(R.drawable.griditembox_offers),
                            new CustomColor(resources.getColor(OffersActivity.ACTIVITY_COLOR)),
                            new Intent(this, OffersActivity.class)
                    );
                    break;
                case 5:
                    gridItem = new BaseGridItem(
                            resources.getString(R.string.activity_settings_name),
                            resources.getDrawable(R.drawable.griditembox_settings),
                            new CustomColor(resources.getColor(SettingsActivity.ACTIVITY_COLOR)),
                            new Intent(this, SettingsActivity.class)
                    );
                    break;
            }

            listGridItem.add(gridItem);
        }

        menuGrid.setListGridItem(listGridItem);
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