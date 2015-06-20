package flousy.gui.widget.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.diderot.android.flousy.R;

/**
 * <b>AbstractRecycler is a container class for a squad of elements (RecyclerItem)</b>
 * <p>
 * A container has :
 * <ul>
 * <li>A context for the adaptor inflater (RecyclerAdapter)</li>
 * <li>A list of elements (AbstractRecycler!item) (</li>
 * <li>A layout resource (XML) for items</li>
 * </ul>
 * </p>
 *
 * Created by Samir on 13/03/2015.
 */
public abstract class Recycler {

    protected Context context;
    private ListRecyclerItems listRecyclerItems;
    private int recyclerItemLayout;

    protected RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    protected Recycler(Context context) {
        this.context = context;
        this.listRecyclerItems = new ListRecyclerItems();
        this.recyclerItemLayout = R.layout.recyclerviewitem;

        this.recyclerAdapter = new RecyclerAdapter(this.listRecyclerItems, this.recyclerItemLayout);
    }

    public boolean addItem(RecyclerItem recyclerItem) {
        boolean added = this.listRecyclerItems.add(recyclerItem);

        try {
            this.recyclerAdapter.notifyDataSetChanged();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return added;
    }

    public boolean removeItem(RecyclerItem recyclerItem) {
        boolean removed = this.listRecyclerItems.remove(recyclerItem);

        try {
            this.recyclerAdapter.notifyDataSetChanged();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return removed;
    }

    public RecyclerItem removeItem(int index) {
        RecyclerItem recyclerItem = this.listRecyclerItems.remove(index);

        try {
            this.recyclerAdapter.notifyDataSetChanged();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return recyclerItem;
    }

    public RecyclerItem getItem(int index) {
        return this.listRecyclerItems.get(index);
    }

    public int size() {
        return this.listRecyclerItems.size();
    }

    public void clearItems() {
        this.listRecyclerItems.clear();

        try {
            this.recyclerAdapter.notifyDataSetChanged();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void adapt(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;

        this.recyclerAdapter.registerAdapterDataObserver(new RecyclerAdapterDataObserver(recyclerView));
        recyclerView.setAdapter(this.recyclerAdapter);
    }
}