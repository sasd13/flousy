package flousy.gui.recycler.grid;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.diderot.android.flousy.R;

import flousy.gui.recycler.AbstractRecycler;
import flousy.gui.recycler.RecyclerAdapter;

/**
 * Created by Samir on 13/03/2015.
 */
public class Grid extends AbstractRecycler {

    public Grid(Context context) {
        super(context);
    }

    @Override
    public void adapt(RecyclerView gridView) {
        setView(gridView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        gridView.setHasFixedSize(true);

        // add spaces item decoration
        int space = getContext().getResources().getDimensionPixelSize(R.dimen.grid_items_space);
        gridView.addItemDecoration(new SpacesItemDecoration(space));

        // use a grid layout manager
        int numColumns = getContext().getResources().getInteger(R.integer.grid_numcolumns);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), numColumns);
        gridView.setLayoutManager(layoutManager);

        RecyclerAdapter gridAdapter = new RecyclerAdapter(getListAbstractRecyclerItem(), getItemStubLayout());
        gridView.setAdapter(gridAdapter);
    }
}