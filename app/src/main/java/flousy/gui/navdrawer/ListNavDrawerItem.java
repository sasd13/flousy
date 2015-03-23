package flousy.gui.navdrawer;

import java.util.ArrayList;

/**
 * Created by Samir on 22/03/2015.
 */
public class ListNavDrawerItem extends ArrayList<AbstractNavDrawerItem> {

    public ListNavDrawerItem() {
        super();
    }

    @Override
    public AbstractNavDrawerItem get(int index) {
        if(index < 0 || index > (this.size() - 1)) {
            return null;
        }

        return super.get(index);
    }

    @Override
    public boolean add(AbstractNavDrawerItem abstractNavDrawerItem) {
        if(this.contains(abstractNavDrawerItem) == true) {
            return false;
        }

        return super.add(abstractNavDrawerItem);
    }

    @Override
    public boolean remove(Object object) {
        if(object instanceof AbstractNavDrawerItem == false) {
            return false;
        }

        return super.remove(object);
    }
}
