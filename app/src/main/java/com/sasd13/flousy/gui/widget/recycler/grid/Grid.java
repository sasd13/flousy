package com.sasd13.flousy.gui.widget.recycler.grid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.sasd13.flousy.R;

import com.sasd13.flousy.gui.widget.recycler.Recycler;

/**
 * <p>
 * Container class for grids :
 * </p>
 * Created by Samir on 13/03/2015.
 */
public class Grid extends Recycler {

    public Grid(Context context) {
        super(context);
    }

    @Override
    public void adapt(RecyclerView recyclerView) {
        super.adapt(recyclerView);

        // add spaces item decoration
        int space = context.getResources().getDimensionPixelSize(R.dimen.grid_items_space);
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));

        int spanCount = context.getResources().getInteger(R.integer.grid_numcolumns);

        // use a grid layout manager
        //recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));

        // use a staggered grid layout manager
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL));
    }
}
