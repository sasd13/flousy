package flousy.gui.widget.recycler.drawer;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import flousy.gui.widget.recycler.Recycler;

/**
 * <p>
 * Container class for Navigation drawer :
 * </p>
 * Created by Samir on 23/03/2015.
 */
public class Drawer extends Recycler {

    private DrawerLayout drawerLayout;

    public Drawer(Context context, DrawerLayout drawerLayout) {
        super(context);

        this.drawerLayout = drawerLayout;
    }

    @Override
    public void adapt(RecyclerView recyclerView) {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        super.adapt(recyclerView);
    }

    public boolean isOpened() {
        return this.drawerLayout.isDrawerOpen(this.recyclerView);
    }

    public void setOpened(boolean opened) {
        if (opened) {
            this.drawerLayout.openDrawer(this.recyclerView);
        } else {
            this.drawerLayout.closeDrawer(this.recyclerView);
        }
    }

    public void setEnabled(boolean enabled) {
        if (enabled) {
            this.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            this.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }
}
