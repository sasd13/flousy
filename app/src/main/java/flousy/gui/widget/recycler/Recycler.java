package flousy.gui.widget.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.flousy.R;

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
    private RecyclerAdapter recyclerAdapter;
    protected RecyclerView recyclerView;

    protected Recycler(Context context) {
        this.context = context;
        this.listRecyclerItems = new ListRecyclerItems();
        this.recyclerAdapter = new RecyclerAdapter(this.listRecyclerItems, R.layout.recyclerviewitem);
    }

    public void addItem(RecyclerItem recyclerItem) {
        this.listRecyclerItems.add(recyclerItem);

        try {
            this.recyclerAdapter.notifyDataSetChanged();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void removeItem(RecyclerItem recyclerItem) {
        this.listRecyclerItems.remove(recyclerItem);

        try {
            this.recyclerAdapter.notifyDataSetChanged();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
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

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(false);

        this.recyclerAdapter.registerAdapterDataObserver(new RecyclerAdapterDataObserver(recyclerView));
        recyclerView.setAdapter(this.recyclerAdapter);
    }
}