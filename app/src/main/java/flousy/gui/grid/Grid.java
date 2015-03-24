package flousy.gui.grid;

import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Samir on 13/03/2015.
 */
public abstract class Grid {

     private ArrayList<GridItem> listGridItem;

    protected Grid() {
        this.listGridItem = new ArrayList<GridItem>();
    }

    public GridItem getItem(int index) {
        if(index < 0 || index > (this.listGridItem.size() - 1)) {
            return null;
        }

        return this.listGridItem.get(index);
    }

    public boolean addItem(GridItem gridItem) {
        if(this.listGridItem.contains(gridItem) == true) {
            return false;
        }

        return this.listGridItem.add(gridItem);
    }

    public GridItem removeItem(int index) {
        if(index < 0 || index > (this.listGridItem.size() - 1)) {
            return null;
        }

        return this.listGridItem.remove(index);
    }

    public int count() {
        return this.listGridItem.size();
    }

    public abstract void adapt(GridView gridView);
}