package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.gui.widget.recycler.grid.Grid;
import com.sasd13.androidex.gui.widget.recycler.grid.GridItem;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.gui.nav.Nav;
import com.sasd13.flousy.gui.nav.NavItem;

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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.home_recyclerview);

        grid = new Grid(recyclerView, R.layout.griditem);
    }

    private void fillGridNav() {
        Nav nav = Nav.getInstance(this);
        GridItem gridItem;

        for (NavItem navItem : nav.getItems()) {
            gridItem = new GridItem();

            gridItem.setText(navItem.getText());
            gridItem.setImage(navItem.getImage());
            gridItem.setColor(navItem.getColor());
            gridItem.setIntent(navItem.getIntent());

            grid.addItem(gridItem);
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
        String firstName = getIntent().getStringExtra(Extra.FIRSTNAME);

        CustomDialog.showOkDialog(
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