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
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(false);

        // use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        super.adapt(recyclerView);
    }
}
