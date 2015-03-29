package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import flousy.gui.actionbar.ActionBar;
import flousy.gui.recycler.grid.GridItem;
import flousy.gui.recycler.grid.Grid;
import flousy.gui.recycler.RecyclerFactory;
import flousy.gui.recycler.RecyclerType;
import flousy.gui.widget.CustomDialogBuilder;

public class MenuActivity extends MotherActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set ActivityContent
        setContentView(R.layout.grid);

        //Set CustomActionBar
        ActionBar actionBar = getCustomActionBar();
        actionBar.getTitleView().setText(R.string.activity_menu_name);
        actionBar.setActionUpButtonEnabled(false);

        //Set Activity content
        RecyclerView gridView = (RecyclerView) findViewById(R.id.grid_view);
        Grid menuGrid = (Grid) RecyclerFactory.create(RecyclerType.GRID, this);
        menuGrid.adapt(gridView);

        GridItem gridItem;
        Resources resources = getResources();

        for(int i=0; i<6; i++) {
            gridItem = new GridItem();
            switch(i) {
                case 0:
                    gridItem.setColor(resources.getColor(NewActivity.ACTIVITY_COLOR));
                    gridItem.setText(resources.getString(R.string.activity_new_name));
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_new));
                    gridItem.setIntent(new Intent(this, NewActivity.class));
                    break;
                case 1:
                    gridItem.setColor(resources.getColor(ConsultActivity.ACTIVITY_COLOR));
                    gridItem.setText(resources.getString(R.string.activity_consult_name));
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_consult));
                    gridItem.setIntent(new Intent(this, ConsultActivity.class));
                    break;
                case 2:
                    gridItem.setColor(resources.getColor(FinancesActivity.ACTIVITY_COLOR));
                    gridItem.setText(resources.getString(R.string.activity_finances_name));
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_finances));
                    gridItem.setIntent(new Intent(this, FinancesActivity.class));
                    break;
                case 3:
                    gridItem.setColor(resources.getColor(FriendsActivity.ACTIVITY_COLOR));
                    gridItem.setText(resources.getString(R.string.activity_friends_name));
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_friends));
                    gridItem.setIntent(new Intent(this, FriendsActivity.class));
                    break;
                case 4:
                    gridItem.setColor(resources.getColor(OffersActivity.ACTIVITY_COLOR));
                    gridItem.setText(resources.getString(R.string.activity_offers_name));
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_offers));
                    gridItem.setIntent(new Intent(this, OffersActivity.class));
                    break;
                case 5:
                    gridItem.setColor(resources.getColor(SettingsActivity.ACTIVITY_COLOR));
                    gridItem.setText(resources.getString(R.string.activity_settings_name));
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_settings));
                    gridItem.setIntent(new Intent(this, SettingsActivity.class));
                    break;
            }

            menuGrid.addItem(gridItem);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent().hasExtra("WELCOME") && getIntent().getBooleanExtra("WELCOME", false) == true) {
            getIntent().removeExtra("WELCOME");

            CharSequence firstName = getIntent().getCharSequenceExtra("NEW_USER_FIRSTNAME");

            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
            builder.setTitle(R.string.menu_alertdialog_welcome_title)
                    .setMessage(getResources().getString(R.string.menu_alertdialog_welcome_message) + " " + firstName + " !")
                    .setNeutralButton(null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (getIntent().hasExtra("EXIT") && getIntent().getBooleanExtra("EXIT", false) == true) {
            getIntent().removeExtra("EXIT");

            Intent intent = new Intent(this, LogInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
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
        return super.onOptionsItemSelected(item);
    }
}