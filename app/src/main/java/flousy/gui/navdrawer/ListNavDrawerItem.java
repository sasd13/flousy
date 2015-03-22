package flousy.gui.navdrawer;

import java.util.ArrayList;

/**
 * Created by Samir on 22/03/2015.
 */
public class ListNavDrawerItem extends ArrayList<NavDrawerItem> {

    public ListNavDrawerItem() {
        super();
    }

    @Override
    public NavDrawerItem get(int index) {
        if(index < 0 || index > (this.size() - 1)) {
            return null;
        }

        return super.get(index);
    }

    @Override
    public boolean add(NavDrawerItem navDrawerItem) {
        if(this.contains(navDrawerItem) == true) {
            return false;
        }

        return super.add(navDrawerItem);
    }

    @Override
    public boolean remove(Object object) {
        if(object instanceof NavDrawerItem == false) {
            return false;
        }

        return super.remove(object);
    }
}
