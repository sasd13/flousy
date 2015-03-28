package flousy.gui.recycler.grid;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import flousy.gui.recycler.AbstractRecycler;
import flousy.gui.recycler.RecyclerAdapter;

/**
 * Created by Samir on 13/03/2015.
 */
public class Grid extends AbstractRecycler {

    public Grid() {
        super();
    }

    public void adapt(RecyclerView gridView) {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        gridView.setHasFixedSize(true);

        // use a linear layout manager
        gridView.setLayoutManager(new GridLayoutManager(gridView.getContext(), GridLayoutManager.DEFAULT_SPAN_COUNT, GridLayoutManager.HORIZONTAL, false));

        RecyclerAdapter gridAdapter = new RecyclerAdapter(getListAbstractRecyclerItem(), getItemStubLayout());
        gridView.setAdapter(gridAdapter);
    }
}