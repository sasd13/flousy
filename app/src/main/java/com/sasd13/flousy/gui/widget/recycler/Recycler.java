package com.sasd13.flousy.gui.widget.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.sasd13.flousy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>AbstractRecycler is a container class for a squad of elements (RecyclerItem)</b>
 * <p>
 * A container has :
 * <ul>
 * <li>A context for the adaptor inflater (RecyclerAdapter)</li>
 * <li>A layout resource (XML)</li>
 * <li>A list of items</li>
 * </ul>
 * </p>
 *
 * Created by Samir on 13/03/2015.
 */
public abstract class Recycler {

    protected Context context;
    private RecyclerView recyclerView;
    private List<RecyclerItem> listRecyclerItems;
    private RecyclerAdapter recyclerAdapter;

    protected Recycler(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.listRecyclerItems = new ArrayList<>();
        this.recyclerAdapter = new RecyclerAdapter(this.listRecyclerItems, R.layout.recycleritem);

        adapt();
    }

    private void adapt() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        this.recyclerView.setHasFixedSize(false);

        this.recyclerAdapter.registerAdapterDataObserver(new RecyclerAdapterDataObserver(this.recyclerView));
        this.recyclerView.setAdapter(this.recyclerAdapter);
    }

    public void addItem(RecyclerItem recyclerItem) {
        this.listRecyclerItems.add(recyclerItem);

        this.recyclerAdapter.notifyDataSetChanged();
    }

    public void removeItem(RecyclerItem recyclerItem) {
        this.listRecyclerItems.remove(recyclerItem);

        this.recyclerAdapter.notifyDataSetChanged();
    }

    public void clearItems() {
        this.listRecyclerItems.clear();

        this.recyclerAdapter.notifyDataSetChanged();
    }

    public RecyclerView getRecyclerView() {
        return this.recyclerView;
    }
}