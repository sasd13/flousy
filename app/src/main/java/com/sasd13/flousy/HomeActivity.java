package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.RecyclerType;
import com.sasd13.androidex.gui.widget.recycler.grid.Grid;
import com.sasd13.androidex.gui.widget.recycler.grid.GridItem;
import com.sasd13.androidex.gui.widget.recycler.grid.GridModel;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.gui.nav.Nav;
import com.sasd13.flousy.gui.nav.NavItem;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

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

        Nav nav = Nav.getInstance(this);

        List<GridItem> gridItems = new ArrayList<>();

        GridItem gridItem;
        GridModel gridModel;

        for (final NavItem navItem : nav.getItems()) {
            gridItem = new GridItem();
            gridModel = new GridModel();

            gridModel.setIcon(navItem.getIcon());
            gridModel.setLabel(navItem.getText());
            gridModel.setColor(navItem.getColor());

            gridItem.setOnClickListener(new RecyclerItem.OnClickListener() {
                @Override
                public void onClick(RecyclerItem recyclerItem) {
                    navItem.getIntent().setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(navItem.getIntent());
                }
            });

            gridModel.addObserver(gridItem);

            gridItems.add(gridItem);
        }

        grid.addAll(gridItems);
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