package com.diderot.android.flousy;

import android.app.AlertDialog;
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

import flousy.gui.activitybar.ActivityBarType;
import flousy.gui.activitybar.BaseActivityBar;
import flousy.gui.color.CustomColor;
import flousy.gui.grid.BaseGrid;
import flousy.gui.grid.BaseGridItem;
import flousy.gui.grid.GridFactory;
import flousy.gui.grid.GridType;
import flousy.gui.grid.ListGridItem;
import flousy.gui.widget.CustomDialogBuilder;

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
        BaseActivityBar activityBar = (BaseActivityBar) createActivityBar(ActivityBarType.BASEBAR);

        //Set ActivityContent
        GridView gridView = (GridView) createActivityContent(R.layout.layout_basegrid);

        BaseGrid menuGrid = (BaseGrid) GridFactory.create(this, GridType.BASEGRID);
        menuGrid.adapt(gridView);

        ListGridItem listGridItem = new ListGridItem();

        BaseGridItem gridItem = null;
        Resources resources = getResources();

        for(int i=0; i<6; i++) {
            switch(i) {
                case 0:
                    gridItem = new BaseGridItem(
                            resources.getString(R.string.activity_new_name),
                            resources.getDrawable(R.drawable.griditem_new),
                            new CustomColor(resources.getColor(NewActivity.ACTIVITY_COLOR)),
                            new Intent(this, NewActivity.class)
                    );
                    break;
                case 1:
                    gridItem = new BaseGridItem(
                            resources.getString(R.string.activity_consult_name),
                            resources.getDrawable(R.drawable.griditem_consult),
                            new CustomColor(resources.getColor(ConsultActivity.ACTIVITY_COLOR)),
                            new Intent(this, ConsultActivity.class)
                    );
                    break;
                case 2:
                    gridItem = new BaseGridItem(
                            resources.getString(R.string.activity_finances_name),
                            resources.getDrawable(R.drawable.griditem_finances),
                            new CustomColor(resources.getColor(FinancesActivity.ACTIVITY_COLOR)),
                            new Intent(this, FinancesActivity.class)
                    );
                    break;
                case 3:
                    gridItem = new BaseGridItem(
                            resources.getString(R.string.activity_friends_name),
                            resources.getDrawable(R.drawable.griditem_friends),
                            new CustomColor(resources.getColor(FriendsActivity.ACTIVITY_COLOR)),
                            new Intent(this, FriendsActivity.class)
                    );
                    break;
                case 4:
                    gridItem = new BaseGridItem(
                            resources.getString(R.string.activity_offers_name),
                            resources.getDrawable(R.drawable.griditem_offers),
                            new CustomColor(resources.getColor(OffersActivity.ACTIVITY_COLOR)),
                            new Intent(this, OffersActivity.class)
                    );
                    break;
                case 5:
                    gridItem = new BaseGridItem(
                            resources.getString(R.string.activity_settings_name),
                            resources.getDrawable(R.drawable.griditem_settings),
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

        if (getIntent().hasExtra("WELCOME") && getIntent().getBooleanExtra("WELCOME", false) == true) {
            getIntent().removeExtra("WELCOME");

            CharSequence firstName = getIntent().getCharSequenceExtra("NEW_USER_FIRSTNAME");

            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
            builder.setTitle(R.string.menu_alertdialog_title_welcome)
                    .setMessage(getResources().getString(R.string.menu_alertdialog_message_welcome) + " " + firstName + " !")
                    .setNeutralButton(null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (getIntent().hasExtra("EXIT") && getIntent().getBooleanExtra("EXIT", false) == true) {
            getIntent().removeExtra("EXIT");

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
        switch (item.getItemId()) {
            default :
                return super.onOptionsItemSelected(item);
        }
    }
}