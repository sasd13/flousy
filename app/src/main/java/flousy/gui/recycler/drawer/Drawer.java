package flousy.gui.recycler.drawer;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import flousy.gui.recycler.AbstractRecycler;
import flousy.gui.recycler.RecyclerAdapter;

/**
 * Created by Samir on 23/03/2015.
 */
public class Drawer extends AbstractRecycler {

    private DrawerLayout drawerLayout;

    public Drawer(Context context) {
        super(context);

        this.drawerLayout = null;
    }

    public DrawerLayout getDrawerLayout() {
        return this.drawerLayout;
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }

    @Override
    public void adapt(RecyclerView drawerView) {
        setView(drawerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        drawerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        drawerView.setLayoutManager(layoutManager);

        RecyclerAdapter drawerAdapter = new RecyclerAdapter(getListAbstractRecyclerItem(), getItemStubLayout());
        drawerView.setAdapter(drawerAdapter);
    }

    public void open() {
        this.drawerLayout.openDrawer(getView());
    }

    public void close() {
        this.drawerLayout.closeDrawer(getView());
    }

    public boolean isOpened() {
        return this.drawerLayout.isDrawerOpen(getView());
    }

    public void setEnabled(boolean enabled) {
        if(enabled) {
            this.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            this.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }
}
