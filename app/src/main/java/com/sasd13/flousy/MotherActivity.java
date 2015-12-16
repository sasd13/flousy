package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;

import com.sasd13.androidx.gui.widget.dialog.CustomDialog;
import com.sasd13.androidx.gui.widget.dialog.WaitDialog;
import com.sasd13.androidx.gui.widget.recycler.drawer.Drawer;
import com.sasd13.androidx.gui.widget.recycler.drawer.DrawerItemTitle;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.gui.content.homemenu.HomeMenuItem;
import com.sasd13.flousy.gui.content.homemenu.HomeMenu;
import com.sasd13.flousy.gui.widget.recycler.drawer.DrawerItemHomeMenu;
import com.sasd13.flousy.session.Session;

public abstract class MotherActivity extends ActionBarActivity {

    private static final int LOGOUT_TIMEOUT = 2000;

    private Drawer drawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setContentView(R.layout.activity_mother);

        createDrawer();
        fillDrawer();
    }

    @Override
    public void setContentView(int layoutResource) {
        ViewStub viewStub = (ViewStub) findViewById(R.id.activity_viewstub);
        viewStub.setLayoutResource(layoutResource);
        viewStub.inflate();
    }

    private void createDrawer() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.drawer_recyclerview);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_drawerlayout);

        this.drawer = new Drawer(this, recyclerView, drawerLayout);
    }

    private void fillDrawer() {
        DrawerItemTitle drawerItemTitle = new DrawerItemTitle();
        drawerItemTitle.setText(getResources().getString(R.string.activity_home));
        this.drawer.addItem(drawerItemTitle);

        addHomeMenuItemsToDrawer();
    }

    private void addHomeMenuItemsToDrawer() {
        HomeMenu homeMenu = HomeMenu.getInstance(this);

        DrawerItemHomeMenu drawerItemHomeMenu;
        for (HomeMenuItem homeMenuItem : homeMenu.getItems()) {
            drawerItemHomeMenu = new DrawerItemHomeMenu();

            drawerItemHomeMenu.setColor(homeMenuItem.getColor());
            drawerItemHomeMenu.setText(homeMenuItem.getText());
            drawerItemHomeMenu.setIntent(homeMenuItem.getIntent());

            this.drawer.addItem(drawerItemHomeMenu);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.drawer.setOpened(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logOut() {
        if (Session.logOut()) {
            goToHomeActivityAndExit();
        } else {
            CustomDialog.showOkDialog(this, "Error logout", "You have not been logged out");
        }
    }

    private void goToHomeActivityAndExit() {
        final WaitDialog waitDialog = new WaitDialog(this);

        final Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Extra.EXIT, true);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
                waitDialog.dismiss();
                finish();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, LOGOUT_TIMEOUT);

        waitDialog.show();
    }
}
