package com.sasd13.flousy.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.grid.EnumGridItemType;
import com.sasd13.androidex.gui.widget.recycler.grid.EnumGridType;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.R;
import com.sasd13.flousy.activities.LogInActivity;
import com.sasd13.flousy.view.gui.browser.Browser;
import com.sasd13.flousy.view.gui.browser.BrowserItemModel;
import com.sasd13.flousy.util.Extra;
import com.sasd13.flousy.util.SessionHelper;

import java.util.List;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_home, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        Recycler grid = RecyclerFactory
                .makeBuilder(EnumGridType.GRID)
                .build((RecyclerView) findViewById(R.id.home_recyclerview));

        fillGrid(grid);
    }

    private void fillGrid(Recycler grid) {
        List<BrowserItemModel> browserItemModels = Browser.getInstance().getItems(this);
        RecyclerHolder recyclerHolder = new RecyclerHolder();
        RecyclerHolderPair pair;

        for (final BrowserItemModel browserItemModel : browserItemModels) {
            browserItemModel.setItemType(EnumGridItemType.GRID);

            pair = new RecyclerHolderPair(browserItemModel);
            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    startActivity(new Intent(HomeFragment.this, browserItemModel.getTarget()));
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
                startActivity(new Intent(HomeFragment.this, LogInActivity.class));
                waitDialog.dismiss();
                finish();
                self = null;
            }
        }, GUIConstants.TIMEOUT_ACTIVITY).start();

        waitDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_home_action_logout:
                SessionHelper.logOut(this);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}