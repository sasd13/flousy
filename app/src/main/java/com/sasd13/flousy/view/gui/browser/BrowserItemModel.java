package com.sasd13.flousy.view.gui.browser;

import android.graphics.drawable.Drawable;

import com.sasd13.androidex.gui.widget.IColorable;
import com.sasd13.androidex.gui.widget.IIconifiable;
import com.sasd13.androidex.gui.widget.ILabelizable;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.flousy.view.IController;

import java.util.Observable;

/**
 * Created by ssaidali2 on 07/07/2016.
 */
public class BrowserItemModel extends Observable implements IRecyclerItemModel, ILabelizable, IIconifiable, IColorable {

    private EnumBrowserItemType browserItemType;
    private String label;
    private Drawable icon;
    private int color;
    private Class<? extends IController> target;

    public BrowserItemModel(EnumBrowserItemType browserItemType, String label, Drawable icon, int color, Class<? extends IController> target) {
        this.browserItemType = browserItemType;
        this.label = label;
        this.icon = icon;
        this.color = color;
        this.target = target;
    }

    @Override
    public IRecyclerItemType getItemType() {
        return browserItemType.getRecyclerItemType();
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

    public Class<? extends IController> getTarget() {
        return target;
    }
}
