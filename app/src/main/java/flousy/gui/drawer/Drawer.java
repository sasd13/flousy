package flousy.gui.drawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.diderot.android.flousy.R;

import java.util.ArrayList;

/**
 * Created by Samir on 23/03/2015.
 */
public class Drawer {

    private Context context;
    private ArrayList<AbstractDrawerItem> listAbstractDrawerItem;
    private int itemStubLayout;

    public Drawer(Context context) {
        this.context = context;
        this.listAbstractDrawerItem = new ArrayList<AbstractDrawerItem>();
        this.itemStubLayout = R.layout.drawer_layout_itemstub;
    }

    public Drawer(Context context, int itemStubLayout) {
        this.listAbstractDrawerItem = new ArrayList<AbstractDrawerItem>();
        this.itemStubLayout = itemStubLayout;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getItemStubLayout() {
        return this.itemStubLayout;
    }

    public void setItemStubLayout(int itemStubLayout) {
        this.itemStubLayout = itemStubLayout;
    }

    public AbstractDrawerItem getItem(int index) {
        if(index < 0 || index > (this.listAbstractDrawerItem.size() - 1)) {
            return null;
        }

        return this.listAbstractDrawerItem.get(index);
    }

    public boolean addItem(AbstractDrawerItem abstractDrawerItem) {
        if(this.listAbstractDrawerItem.contains(abstractDrawerItem) == true) {
            return false;
        }

        return this.listAbstractDrawerItem.add(abstractDrawerItem);
    }

    public AbstractDrawerItem removeItem(int index) {
        if(index < 0 || index > (this.listAbstractDrawerItem.size() - 1)) {
            return null;
        }

        return this.listAbstractDrawerItem.remove(index);
    }

    public int count() {
        return this.listAbstractDrawerItem.size();
    }

    public void adapt(RecyclerView drawerLayout) {
        DrawerAdapter drawerAdapter = new DrawerAdapter(this.context, this.listAbstractDrawerItem, this.itemStubLayout);
        drawerLayout.setAdapter(drawerAdapter);
    }
}
