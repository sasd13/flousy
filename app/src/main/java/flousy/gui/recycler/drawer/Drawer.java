package flousy.gui.recycler.drawer;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import flousy.gui.recycler.AbstractRecycler;
import flousy.gui.recycler.RecyclerAdapter;

/**
 * Created by Samir on 23/03/2015.
 */
public class Drawer extends AbstractRecycler {

    private static Drawer instance = null;

    private Drawer() {
        super();
    }

    public static synchronized Drawer getInstance() {
        if(instance == null) {
            instance = new Drawer();
        }

        return instance;
    }

    public void adapt(RecyclerView drawerView) {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        drawerView.setHasFixedSize(true);

        // use a linear layout manager
        drawerView.setLayoutManager(new LinearLayoutManager(drawerView.getContext()));

        RecyclerAdapter gridAdapter = new RecyclerAdapter(getListAbstractRecyclerItem(), getItemStubLayout());
        drawerView.setAdapter(gridAdapter);
    }
}
