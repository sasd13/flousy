package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.gui.content.HomeMenuItem;
import com.sasd13.flousy.gui.content.HomeMenuItems;
import com.sasd13.flousy.gui.widget.dialog.CustomDialog;
import com.sasd13.flousy.gui.widget.recycler.grid.Grid;
import com.sasd13.flousy.gui.widget.recycler.grid.GridItem;

public class HomeActivity extends MotherActivity {

    private Grid grid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview);

        createGridHomeMenu();
        fillGridHomeMenu();
    }

    private void createGridHomeMenu() {
        this.grid = new Grid(this);

        RecyclerView gridView = (RecyclerView) findViewById(R.id.recyclerview);
        this.grid.adapt(gridView);
    }

    private void fillGridHomeMenu() {
        HomeMenuItems homeMenuItems = HomeMenuItems.getInstance(this);

        GridItem gridItem;
        for (HomeMenuItem homeMenuItem : homeMenuItems.getItems()) {
            gridItem = new GridItem();

            gridItem.setText(homeMenuItem.getText());
            gridItem.setImage(homeMenuItem.getImage());
            gridItem.setColor(homeMenuItem.getColor());
            gridItem.setIntent(homeMenuItem.getIntent());

            this.grid.addItem(gridItem);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent().hasExtra(Extra.WELCOME) && getIntent().getBooleanExtra(Extra.WELCOME, false)) {
            getIntent().removeExtra(Extra.WELCOME);

            showWelcome();
        } else if (getIntent().hasExtra(Extra.EXIT) && getIntent().getBooleanExtra(Extra.EXIT, false)) {
            getIntent().removeExtra(Extra.EXIT);

            exit();
        }
    }

    private void showWelcome() {
        String firstName = getIntent().getStringExtra(Extra.USER_FIRSTNAME);

        CustomDialog.showOkDialog(
                this,
                getResources().getString(R.string.home_alertdialog_welcome_title),
                getResources().getString(R.string.home_alertdialog_welcome_message) + " " + firstName + " !");
    }

    private void exit() {
        Intent intent = new Intent(this, LogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home_action_logout:
                logOut();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}