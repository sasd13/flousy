package flousy.util.grid;

import android.util.Log;

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
        GridItem gridItem;

        try {
            gridItem = super.get(index);
        } catch (IndexOutOfBoundsException e) {
            gridItem = null;
            Log.println(1, null, "ListGridItem error item index : "+index);
        }

        return gridItem;
    }

    @Override
    public boolean add(GridItem gridItem) {
        if(this.contains(gridItem) == false) {
            return super.add(gridItem);
        }

        return false;
    }

    @Override
    public boolean remove(Object object) {
        if(object instanceof GridItem) {
            return super.remove(object);
        }

        return false;
    }
}