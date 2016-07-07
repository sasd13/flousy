package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.grid.Grid;
import com.sasd13.androidex.gui.widget.recycler.grid.GridFactory;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.gui.recycler.browser.Browser;
import com.sasd13.flousy.gui.recycler.browser.BrowserModel;

public class HomeActivity extends MotherActivity {

    public static HomeActivity self;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self = this;

        setContentView(R.layout.activity_home);
        buildHomeView();
    }

    private void buildHomeView() {
        GridFactory gridFactory = new GridFactory(this);
        Grid grid = (Grid) gridFactory.makeBuilder().build((RecyclerView) findViewById(R.id.home_recyclerview));

        fillGridNav(grid, gridFactory);
    }

    private void fillGridNav(Grid grid, GridFactory gridFactory) {
        BrowserModel[] items = Browser.getInstance().getItems();

        RecyclerHolder recyclerHolder = new RecyclerHolder();
        recyclerHolder.add(items);

        RecyclerHelper.fill(grid, recyclerHolder, gridFactory);
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
        String firstName = getIntent().getStringExtra(Extra.FIRSTNAME);

        OptionDialog.showOkDialog(
                this,
                getResources().getString(R.string.home_alertdialog_title_welcome),
                getResources().getString(R.string.home_alertdialog_message_welcome) + " " + firstName + " !");
    }

    public void exit() {
        final WaitDialog waitDialog = new WaitDialog(this);
        waitDialog.setCancelable(false);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(HomeActivity.this, LogInActivity.class));
                waitDialog.dismiss();
                finish();
            }
        }, GUIConstants.TIMEOUT_ACTIVITY).start();

        waitDialog.show();
    }
}