package flousy.util.grid;

import java.util.ArrayList;

/**
 * Created by Samir on 21/02/2015.
 */
public class ListGridItem extends ArrayList<GridItem> {

    public ListGridItem() {
        super();
    }

    @Override
    public GridItem get(int index) {
        if(index < 0 || index > (this.size() - 1)) {
            return null;
        }

        return super.get(index);
    }

    @Override
    public boolean add(GridItem gridItem) {
        if(this.contains(gridItem) == true) {
            return false;
        }

        return super.add(gridItem);
    }

    @Override
    public boolean remove(Object object) {
        if(object instanceof GridItem == false) {
            return false;
        }

        return super.remove(object);
    }
}