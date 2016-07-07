package com.sasd13.flousy.gui.recycler.browser;

import android.graphics.drawable.Drawable;

import com.sasd13.androidex.gui.Action;
import com.sasd13.androidex.gui.widget.IClickable;
import com.sasd13.androidex.gui.widget.IItemType;
import com.sasd13.androidex.gui.widget.recycler.IColoredModel;
import com.sasd13.androidex.gui.widget.recycler.IIconifiedModel;
import com.sasd13.androidex.gui.widget.recycler.ILabelizedModel;

import java.util.Observable;

/**
 * Created by ssaidali2 on 07/07/2016.
 */
public class BrowserModel extends Observable implements ILabelizedModel, IIconifiedModel, IColoredModel, IClickable {

    private IItemType itemType;
    private String label;
    private Drawable icon;
    private int color;
    private Action action;

    public BrowserModel(String label, Drawable icon, int color, Action action) {
        this.label = label;
        this.icon = icon;
        this.color = color;
        this.action = action;
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

    @Override
    public Action getActionClick() {
        return action;
    }
}
