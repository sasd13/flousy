package com.sasd13.flousy.gui.recycler.browser;

import android.graphics.drawable.Drawable;

import com.sasd13.androidex.gui.Action;
import com.sasd13.androidex.gui.widget.IItemType;
import com.sasd13.androidex.gui.widget.recycler.IColoredModel;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItemType;

/**
 * Created by ssaidali2 on 07/07/2016.
 */
public class BrowserGridModel extends BrowserModel implements IColoredModel {

    private int color;

    private BrowserGridModel(String label, Drawable icon, Action action, int color) {
        super(label, icon, action);

        this.color = color;
    }

    @Override
    public IItemType getItemType() {
        return RecyclerItemType.GRID;
    }

    @Override
    public int getColor() {
        return color;
    }
}
