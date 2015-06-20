package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import flousy.constant.Extra;
import flousy.gui.actionbar.ActionBar;
import flousy.gui.content.FlousyMenu;
import flousy.gui.content.ListFlousyMenus;
import flousy.gui.widget.dialog.CustomDialog;
import flousy.gui.widget.dialog.CustomDialogBuilder;
import flousy.gui.widget.recycler.grid.Grid;
import flousy.gui.widget.recycler.grid.GridItem;
import flousy.session.Session;

public class MenuActivity extends MotherActivity {

    private static final int LOGOUT_TIME_OUT = 2000;

    private Grid grid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set ActivityContent
        setContentView(R.layout.recyclerview);

        //Set CustomActionBar
        ActionBar actionBar = getCustomActionBar();
        actionBar.getTitleView().setText(R.string.activity_menu_name);
        actionBar.setActionUpButtonEnabled(false);

        ImageButton buttonLogout = actionBar.getActionFirstButton();
        buttonLogout.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_logout));
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
        actionBar.setActionFirstButtonEnabled(true);

        //Disable Drawer
        actionBar.setActionDrawerButtonEnabled(false);
        getDrawer().setEnabled(false);

        //Set Activity content
        this.grid = new Grid(this);

        RecyclerView gridView = (RecyclerView) findViewById(R.id.recyclerview);
        this.grid.adapt(gridView);

        initialize();
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

    private void logOut() {
        if (Session.logOut()) {
            goToLoginActivity();
        } else {
            CustomDialog.showOkDialog(this, "Error logout", "You have not been logged out");
        }
    }

    public void initialize() {
        super.initialize();

        createGridForFlousyMenus();
    }

    private void createGridForFlousyMenus() {
        this.grid.clearItems();

        ListFlousyMenus listFlousyMenus = ListFlousyMenus.getInstance(this);

        GridItem gridItem;
        for(Object flousyMenu : listFlousyMenus) {
            gridItem = new GridItem();

            gridItem.setColor(((FlousyMenu) flousyMenu).getColor());
            gridItem.setImage(((FlousyMenu) flousyMenu).getImage());
            gridItem.setText(((FlousyMenu) flousyMenu).getName());
            gridItem.setIntent(((FlousyMenu) flousyMenu).getIntent());

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

        final Intent intent = new Intent(this, LogInActivity.class);
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