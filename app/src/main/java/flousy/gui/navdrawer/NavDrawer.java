package flousy.gui.navdrawer;

import android.content.Context;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Samir on 23/03/2015.
 */
public abstract class NavDrawer {

    private Context context;
    private ArrayList<NavDrawerItem> listNavDrawerItem;

    protected NavDrawer(Context context) {
        this.context = context;
        this.listNavDrawerItem = new ArrayList<NavDrawerItem>();
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public NavDrawerItem getItem(int index) {
        if(index < 0 || index > (this.listNavDrawerItem.size() - 1)) {
            return null;
        }

        return this.listNavDrawerItem.get(index);
    }

    public boolean addItem(NavDrawerItem navDrawerItem) {
        if(this.listNavDrawerItem.contains(navDrawerItem) == true) {
            return false;
        }

        return this.listNavDrawerItem.add(navDrawerItem);
    }

    public boolean removeItem(Object object) {
        if(object instanceof NavDrawerItem == false) {
            return false;
        }

        return this.listNavDrawerItem.remove(object);
    }

    public int count() {
        return this.listNavDrawerItem.size();
    }

    public abstract void adapt(ListView listView);
}
