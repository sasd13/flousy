package com.sasd13.flousy.gui.widget.recycler.tab;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sasd13.flousy.gui.widget.recycler.Recycler;

/**
 * <p>
 * Container class for Table views :
 * </p>
 * Created by Samir on 22/05/2015.
 */
public class Tab extends Recycler {

    public Tab(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
}
