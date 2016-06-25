package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.sasd13.androidex.gui.Action;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerType;
import com.sasd13.androidex.gui.widget.recycler.grid.Grid;
import com.sasd13.androidex.gui.widget.recycler.grid.GridModel;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.gui.browser.Browser;

public class HomeActivity extends MotherActivity {

    private Grid grid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        createGridNav();
        fillGridNav();
    }

    private void createGridNav() {
        grid = (Grid) RecyclerHelper.create(RecyclerType.GRID, (RecyclerView) findViewById(R.id.home_recyclerview));
    }

    private void fillGridNav() {
        grid.clear();

        Browser.Item[] items = Browser.getInstance().getItems();
        GridModel[] gridModels = new GridModel[items.length];

        int i=-1;

        for (final Browser.Item item : items) {
            i++;

            gridModels[i] = new GridModel();

            gridModels[i].setIcon(item.getIcon());
            gridModels[i].setLabel(item.getLabel());
            gridModels[i].setColor(item.getColor());
            gridModels[i].setAction(new Action() {
                @Override
                public void execute() {
                    Intent intent = item.getIntent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(intent);
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
                getResources().getString(R.string.home_alertdialog_title_welcome) + " " + firstName + " !");
    }

    private void exit() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        finish();
    }
}