package com.sasd13.flousy.gui.recycler.browser;

import android.graphics.drawable.Drawable;

import com.sasd13.androidex.gui.widget.IItemType;
import com.sasd13.androidex.gui.widget.recycler.IColoredItemModel;
import com.sasd13.androidex.gui.widget.recycler.IIconifiedItemModel;
import com.sasd13.androidex.gui.widget.recycler.ILabelizedItemModel;

import java.util.Observable;

/**
 * Created by ssaidali2 on 07/07/2016.
 */
public class BrowserItemModel extends Observable implements ILabelizedItemModel, IIconifiedItemModel, IColoredItemModel {

    private IItemType itemType;
    private String label;
    private Drawable icon;
    private int color;

    public BrowserItemModel(String label, Drawable icon, int color) {
        this.label = label;
        this.icon = icon;
        this.color = color;
    }

    @Override
    public IItemType getItemType() {
        return itemType;
    }

    public void setItemType(IItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Drawable getIcon() {
        return icon;
    }

    @Override
    public int getColor() {
        return color;
    }
}
