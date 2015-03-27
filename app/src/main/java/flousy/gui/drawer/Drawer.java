package flousy.gui.drawer;

import android.support.v7.widget.RecyclerView;

import com.diderot.android.flousy.R;

import java.util.ArrayList;

/**
 * Created by Samir on 23/03/2015.
 */
public class Drawer {

    private ArrayList<AbstractDrawerItem> listAbstractDrawerItem;
    private int itemStubLayout;

    public Drawer() {
        this.listAbstractDrawerItem = new ArrayList<AbstractDrawerItem>();
        this.itemStubLayout = R.layout.drawer_layout_itemstub;
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
        DrawerAdapter drawerAdapter = new DrawerAdapter(this.listAbstractDrawerItem, this.itemStubLayout);
        drawerLayout.setAdapter(drawerAdapter);
    }
}
