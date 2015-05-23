package flousy.gui.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diderot.android.flousy.R;

import java.util.ArrayList;

/**
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
        if(index < 0 || index > (this.listAbstractRecyclerItem.size() - 1)) {
            return null;
        }

        return this.listAbstractRecyclerItem.get(index);
    }

    public boolean addItem(AbstractRecyclerItem abstractRecyclerItem) {
        if(this.listAbstractRecyclerItem.contains(listAbstractRecyclerItem) == true) {
            return false;
        }

        return this.listAbstractRecyclerItem.add(abstractRecyclerItem);
    }

    public AbstractRecyclerItem removeItem(int index) {
        if(index < 0 || index > (this.listAbstractRecyclerItem.size() - 1)) {
            return null;
        }

        return this.listAbstractRecyclerItem.remove(index);
    }

    public int count() {
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