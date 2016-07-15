package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.ActionEvent;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.grid.GridItemType;
import com.sasd13.androidex.gui.widget.recycler.grid.GridType;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.gui.recycler.browser.Browser;
import com.sasd13.flousy.gui.recycler.browser.BrowserItemModel;

public class HomeActivity extends MotherActivity {

    public static HomeActivity self;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        self = this;

        buildHomeView();
    }

    private void buildHomeView() {
        Recycler grid = RecyclerFactory.makeBuilder(GridType.GRID).build((RecyclerView) findViewById(R.id.home_recyclerview));

        fillGrid(grid);
    }

    private void fillGrid(Recycler grid) {
        RecyclerHolder recyclerHolder = new RecyclerHolder();
        RecyclerHolderPair pair;
        Browser browser = Browser.getInstance();

        for (final BrowserItemModel browserItemModel : browser.getItems(this)) {
            browserItemModel.setType(GridItemType.GRID);

            pair = new RecyclerHolderPair(browserItemModel);
            pair.addController(ActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    startActivity(new Intent(HomeActivity.this, browserItemModel.getTarget()));
                }
            });

            recyclerHolder.add(pair);
        }

        RecyclerHelper.addAll(grid, recyclerHolder);
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
        StringBuilder builder = new StringBuilder();
        builder.append(getResources().getString(R.string.home_alertdialog_message_welcome));
        builder.append(" ");
        builder.append(getIntent().getStringExtra(Extra.FIRSTNAME));
        builder.append(" !");

        OptionDialog.showOkDialog(this, getResources().getString(R.string.home_alertdialog_title_welcome), builder.toString());
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