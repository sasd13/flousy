package com.example.flousy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import flousy.constant.Extra;
import flousy.gui.content.HomeItem;
import flousy.gui.content.ListHomeItems;
import flousy.gui.widget.dialog.CustomDialog;
import flousy.gui.widget.dialog.CustomDialogBuilder;
import flousy.gui.widget.recycler.grid.Grid;
import flousy.gui.widget.recycler.grid.GridItem;
import flousy.session.Session;

public class HomeActivity extends MotherActivity {

    private static final int LOGOUT_TIME_OUT = 2000;

    private Grid grid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set ActivityContent
        setContentView(R.layout.recyclerview);

        //Set Activity content
        this.grid = new Grid(this);

        RecyclerView gridView = (RecyclerView) findViewById(R.id.recyclerview);
        this.grid.adapt(gridView);

        createGridMenu();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent().hasExtra(Extra.WELCOME) && getIntent().getBooleanExtra(Extra.WELCOME, false)) {
            getIntent().removeExtra(Extra.WELCOME);

            showWelcome();
        } else if (getIntent().hasExtra(Extra.EXIT) && getIntent().getBooleanExtra(Extra.EXIT, false)) {
            getIntent().removeExtra(Extra.EXIT);

            goToLoginActivity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logOut();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void logOut() {
        if (Session.logOut()) {
            goToLoginActivity();
        } else {
            CustomDialog.showOkDialog(this, "Error logout", "You have not been logged out");
        }
    }

    private void createGridMenu() {
        this.grid.clearItems();

        ListHomeItems listHomeItems = ListHomeItems.getInstance(this);

        GridItem gridItem;
        for(HomeItem homeItem : listHomeItems) {
            gridItem = new GridItem();

            gridItem.setColor(homeItem.getColor());
            gridItem.setImage(homeItem.getImage());
            gridItem.setText(homeItem.getName());
            gridItem.setIntent(homeItem.getIntent());

            this.grid.addItem(gridItem);
        }
    }

    private void showWelcome() {
        String firstName = getIntent().getStringExtra(Extra.USER_FIRSTNAME);
        CustomDialog.showOkDialog(
                this,
                getResources().getString(R.string.menu_alertdialog_welcome_title),
                getResources().getString(R.string.menu_alertdialog_welcome_message) + " " + firstName + " !");
    }

    private void goToLoginActivity() {
        CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
        final AlertDialog dialog = builder.create();

        final Intent intent = new Intent(this, UserLogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
                dialog.dismiss();
                finish();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, LOGOUT_TIME_OUT);

        dialog.show();
    }
}