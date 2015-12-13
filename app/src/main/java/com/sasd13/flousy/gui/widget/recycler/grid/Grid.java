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

    public Grid(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);

        // add spaces item decoration
        int space = context.getResources().getDimensionPixelSize(R.dimen.grid_items_space);
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));

        int spanCount = context.getResources().getInteger(R.integer.grid_columns_number);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL));
    }
}
