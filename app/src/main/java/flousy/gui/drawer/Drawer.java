package flousy.gui.drawer;

import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Samir on 23/03/2015.
 */
public abstract class Drawer {

    private ArrayList<DrawerItem> listDrawerItem;

    protected Drawer() {
        this.listDrawerItem = new ArrayList<DrawerItem>();
    }

    public DrawerItem getItem(int index) {
        if(index < 0 || index > (this.listDrawerItem.size() - 1)) {
            return null;
        }

        return this.listDrawerItem.get(index);
    }

    public boolean addItem(DrawerItem drawerItem) {
        if(this.listDrawerItem.contains(drawerItem) == true) {
            return false;
        }

        return this.listDrawerItem.add(drawerItem);
    }

    public DrawerItem removeItem(int index) {
        if(index < 0 || index > (this.listDrawerItem.size() - 1)) {
            return null;
        }

        return this.listDrawerItem.remove(index);
    }

    public int count() {
        return this.listDrawerItem.size();
    }

    public abstract void adapt(ListView listView);
}
