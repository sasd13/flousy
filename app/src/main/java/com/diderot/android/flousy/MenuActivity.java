package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageButton;

import flousy.gui.actionbar.ActionBarType;
import flousy.gui.actionbar.BaseActionBar;
import flousy.gui.grid.BaseGridItem;
import flousy.gui.grid.BaseGrid;
import flousy.gui.grid.GridFactory;
import flousy.gui.grid.GridType;
import flousy.gui.grid.ListGridItem;
import flousy.gui.widget.CustomDialogBuilder;

public class MenuActivity extends MotherActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set ActionBar
        BaseActionBar actionBar = (BaseActionBar) createActionBar(ActionBarType.BASEBAR);
        actionBar.setButtonPreviousEnabled(false);
        actionBar.getTextViewTitle().setText(R.string.activity_menu_name);

        ImageButton buttonSearch = actionBar.getImageButtonActionFirst();
        buttonSearch.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_search));

        //Set ActivityContent
        GridView gridView = (GridView) createActivityContent(R.layout.grid_base);

        BaseGrid menuGrid = (BaseGrid) GridFactory.create(this, GridType.BASEGRID);
        menuGrid.adapt(gridView);

        ListGridItem listGridItem = new ListGridItem();

        BaseGridItem baseGridItem = null;
        Resources resources = getResources();

        for(int i=0; i<6; i++) {
            switch(i) {
                case 0:
                    baseGridItem = new BaseGridItem(
                            resources.getString(R.string.activity_new_name),
                            resources.getDrawable(R.drawable.griditem_new),
                            resources.getColor(NewActivity.ACTIVITY_COLOR),
                            new Intent(this, NewActivity.class)
                    );
                    break;
                case 1:
                    baseGridItem = new BaseGridItem(
                            resources.getString(R.string.activity_consult_name),
                            resources.getDrawable(R.drawable.griditem_consult),
                            resources.getColor(ConsultActivity.ACTIVITY_COLOR),
                            new Intent(this, ConsultActivity.class)
                    );
                    break;
                case 2:
                    baseGridItem = new BaseGridItem(
                            resources.getString(R.string.activity_finances_name),
                            resources.getDrawable(R.drawable.griditem_finances),
                            resources.getColor(FinancesActivity.ACTIVITY_COLOR),
                            new Intent(this, FinancesActivity.class)
                    );
                    break;
                case 3:
                    baseGridItem = new BaseGridItem(
                            resources.getString(R.string.activity_friends_name),
                            resources.getDrawable(R.drawable.griditem_friends),
                            resources.getColor(FriendsActivity.ACTIVITY_COLOR),
                            new Intent(this, FriendsActivity.class)
                    );
                    break;
                case 4:
                    baseGridItem = new BaseGridItem(
                            resources.getString(R.string.activity_offers_name),
                            resources.getDrawable(R.drawable.griditem_offers),
                            resources.getColor(OffersActivity.ACTIVITY_COLOR),
                            new Intent(this, OffersActivity.class)
                    );
                    break;
                case 5:
                    baseGridItem = new BaseGridItem(
                            resources.getString(R.string.activity_settings_name),
                            resources.getDrawable(R.drawable.griditem_settings),
                            resources.getColor(SettingsActivity.ACTIVITY_COLOR),
                            new Intent(this, SettingsActivity.class)
                    );
                    break;
            }

            listGridItem.add(baseGridItem);
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