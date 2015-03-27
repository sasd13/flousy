package flousy.gui.grid;

import android.widget.GridView;

import com.diderot.android.flousy.R;

import java.util.ArrayList;

/**
 * Created by Samir on 13/03/2015.
 */
public class Grid {

    private ArrayList<AbstractGridItem> listAbstractGridItem;
    private int itemLayout;

    protected Grid() {
        this.listAbstractGridItem = new ArrayList<AbstractGridItem>();
        this.itemLayout = R.layout.griditem;
    }

    protected Grid(int itemLayout) {
        this.listAbstractGridItem = new ArrayList<AbstractGridItem>();
        this.itemLayout = itemLayout;
    }

    public AbstractGridItem getItem(int index) {
        if(index < 0 || index > (this.listAbstractGridItem.size() - 1)) {
            return null;
        }

        return this.listAbstractGridItem.get(index);
    }

    public boolean addItem(AbstractGridItem abstractGridItem) {
        if(this.listAbstractGridItem.contains(abstractGridItem) == true) {
            return false;
        }

        return this.listAbstractGridItem.add(abstractGridItem);
    }

    public AbstractGridItem removeItem(int index) {
        if(index < 0 || index > (this.listAbstractGridItem.size() - 1)) {
            return null;
        }

        return this.listAbstractGridItem.remove(index);
    }

    public int count() {
        return this.listAbstractGridItem.size();
    }

    public void adapt(GridView gridView) {
        GridAdapter gridAdapter = new GridAdapter(this.listAbstractGridItem, this.itemLayout);
        gridView.setAdapter(gridAdapter);
    }
}