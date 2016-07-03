package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.sasd13.androidex.gui.Action;
import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.grid.Grid;
import com.sasd13.androidex.gui.widget.recycler.grid.GridFactory;
import com.sasd13.androidex.gui.widget.recycler.grid.GridModel;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.Browser;

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
        Browser.Item[] items = Browser.getInstance().getItems();
        GridModel[] gridModels = new GridModel[items.length];

        int i=-1;

        for (final Browser.Item item : items) {
            i++;

            gridModels[i] = new GridModel();
            gridModels[i].setIcon(item.getIcon());
            gridModels[i].setLabel(item.getLabel());
            gridModels[i].setColor(item.getColor());
            gridModels[i].setActionClick(new Action() {
                @Override
                public void execute() {
                    startActivity(item.getIntent());
                }
            });
        }

        RecyclerHolder recyclerHolder = new RecyclerHolder();
        recyclerHolder.add(gridModels);

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