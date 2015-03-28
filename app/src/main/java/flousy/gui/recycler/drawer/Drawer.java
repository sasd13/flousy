package flousy.gui.recycler.drawer;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import flousy.gui.recycler.AbstractRecycler;
import flousy.gui.recycler.RecyclerAdapter;

/**
 * Created by Samir on 23/03/2015.
 */
public class Drawer extends AbstractRecycler {

    public Drawer(Context context) {
        super(context);
    }

    @Override
    public void adapt(RecyclerView drawerView) {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        drawerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        drawerView.setLayoutManager(layoutManager);

        RecyclerAdapter drawerAdapter = new RecyclerAdapter(getListAbstractRecyclerItem(), getItemStubLayout());
        drawerView.setAdapter(drawerAdapter);
    }
}
