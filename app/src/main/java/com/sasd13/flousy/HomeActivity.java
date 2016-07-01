package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.sasd13.androidex.gui.Action;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerType;
import com.sasd13.androidex.gui.widget.recycler.grid.Grid;
import com.sasd13.androidex.gui.widget.recycler.grid.GridModel;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.gui.browser.Browser;

public class HomeActivity extends MotherActivity {

    public static HomeActivity self;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self = this;

        setContentView(R.layout.activity_home);
        createGridNav();
    }

    private void createGridNav() {
        Grid grid = (Grid) RecyclerHelper.create(RecyclerType.GRID, (RecyclerView) findViewById(R.id.home_recyclerview));

        fillGrid(grid);
    }

    private void fillGrid(Grid grid) {
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

        RecyclerHelper.fill(grid, recyclerHolder, this);
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

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                waitDialog.dismiss();
                finish();
            }
        }, 1500).start();

        waitDialog.show();
    }
}