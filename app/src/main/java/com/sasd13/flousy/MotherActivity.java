package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.drawer.Drawer;
import com.sasd13.androidex.session.Session;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.gui.nav.Nav;
import com.sasd13.flousy.gui.nav.NavItem;
import com.sasd13.flousy.gui.widget.recycler.drawer.DrawerItemNav;

public abstract class MotherActivity extends AppCompatActivity {

    private static final int TIMEOUT = 2000;

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

        drawer = new Drawer(recyclerView, drawerLayout);
    }

    private void fillDrawer() {
        /*DrawerItemTitle drawerItemTitle = new DrawerItemTitle();
        drawerItemTitle.setText(getResources().getString(R.string.activity_home));
        drawer.addItem(drawerItemTitle);*/

        addNavItemsToDrawer();
    }

    private void addNavItemsToDrawer() {
        Nav nav = Nav.getInstance(this);
        DrawerItemNav drawerItemNav;

        for (final NavItem navItem : nav.getItems()) {
            drawerItemNav = new DrawerItemNav();

            drawerItemNav.setColor(navItem.getColor());
            drawerItemNav.setText(navItem.getText());
            drawerItemNav.setOnClickListener(new RecyclerItem.ActionListener() {
                @Override
                public void doAction(RecyclerItem recyclerItem) {
                    navItem.getIntent().setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(navItem.getIntent());
                }
            });

            drawer.addItem(drawerItemNav);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        drawer.setOpened(false);
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
        Session.clear();
        goToHomeActivityAndExit();
    }

    private void goToHomeActivityAndExit() {
        final WaitDialog waitDialog = new WaitDialog(this);

        TaskPlanner taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MotherActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Extra.EXIT, true);

                startActivity(intent);
                waitDialog.dismiss();
                finish();
            }
        }, TIMEOUT);

        taskPlanner.start();
        waitDialog.show();
    }
}
