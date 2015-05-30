package flousy.gui.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diderot.android.flousy.R;

import java.util.ArrayList;

/**
 * <b>AbstractRecycler is a container class for a group of elements (AbstractRecyclerItem)</b>
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
public abstract class AbstractRecycler {

    private Context context;
    private ArrayList<AbstractRecyclerItem> listAbstractRecyclerItem;
    private int itemStubLayout;

    private View view;

    protected AbstractRecycler(Context context) {
        this.context = context;
        this.listAbstractRecyclerItem = new ArrayList<AbstractRecyclerItem>();
        this.itemStubLayout = R.layout.recyclerviewitem;
        this.view = null;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    protected ArrayList<AbstractRecyclerItem> getListAbstractRecyclerItem() {
        return this.listAbstractRecyclerItem;
    }

    public AbstractRecyclerItem getItem(int index) {
        return this.listAbstractRecyclerItem.get(index);
    }

    public boolean addItem(AbstractRecyclerItem abstractRecyclerItem) {
        return this.listAbstractRecyclerItem.add(abstractRecyclerItem);
    }

    public AbstractRecyclerItem removeItem(int index) {
        return this.listAbstractRecyclerItem.remove(index);
    }

    public int size() {
        return this.listAbstractRecyclerItem.size();
    }

    public int getItemStubLayout() {
        return this.itemStubLayout;
    }

    public void setItemStubLayout(int itemStubLayout) {
        this.itemStubLayout = itemStubLayout;
    }

    protected View getView() {
        return this.view;
    }

    protected void setView(View view) {
        this.view = view;
    }

    public abstract void adapt(RecyclerView recyclerView);
}