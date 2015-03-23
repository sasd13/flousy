package flousy.gui.grid;

import android.content.Context;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Samir on 13/03/2015.
 */
public abstract class Grid {

    private Context context;
    private ArrayList<GridItem> listGridItem;

    protected Grid(Context context, int itemLayoutResource) {
        this.context = context;
        this.listGridItem = new ArrayList<GridItem>();
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
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

    public boolean removeItem(Object object) {
        if(object instanceof GridItem == false) {
            return false;
        }

        return this.listGridItem.remove(object);
    }

    public int count() {
        return this.listGridItem.size();
    }

    public abstract void adapt(GridView gridView);
}