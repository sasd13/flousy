package flousy.gui.widget.recycler.tab;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import flousy.gui.widget.recycler.Recycler;

/**
 * <p>
 * Container class for Table views :
 * </p>
 * Created by Samir on 22/05/2015.
 */
public class Tab extends Recycler {

    public Tab(Context context) {
        super(context);
    }

    @Override
    public void adapt(RecyclerView recyclerView) {
        super.adapt(recyclerView);

        // use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
}
