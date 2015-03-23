package flousy.gui.navdrawer;

import android.content.Context;

/**
 * Created by Samir on 23/03/2015.
 */
public class NavDrawer {

    private Context context;
    private ListNavDrawerItem listNavDrawerItem;

    public NavDrawer(Context context) {
        this.context = context;
        this.listNavDrawerItem = new ListNavDrawerItem();
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean addChild(AbstractNavDrawerItem item) {
        return this.listNavDrawerItem.add(item);
    }

    public AbstractNavDrawerItem removeChild(int index) {
        return this.listNavDrawerItem.remove(index);
    }

    public AbstractNavDrawerItem getChild(int index) {
        return this.listNavDrawerItem.get(index);
    }

    public int count() {
        return this.listNavDrawerItem.size();
    }
}
