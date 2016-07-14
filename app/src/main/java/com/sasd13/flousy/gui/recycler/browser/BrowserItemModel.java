package com.sasd13.flousy.gui.recycler.browser;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.sasd13.androidex.gui.widget.IColorable;
import com.sasd13.androidex.gui.widget.IIconifiable;
import com.sasd13.androidex.gui.widget.ILabelizable;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemModel;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;

import java.util.Observable;

/**
 * Created by ssaidali2 on 07/07/2016.
 */
public class BrowserItemModel extends Observable implements IRecyclerItemModel, ILabelizable, IIconifiable, IColorable {

    private IRecyclerItemType type;
    private String label;
    private Drawable icon;
    private int color;
    private Class<? extends Activity> target;

    public BrowserItemModel(String label, Drawable icon, int color, Class<? extends Activity> target) {
        this.label = label;
        this.icon = icon;
        this.color = color;
        this.target = target;
    }

    @Override
    public IRecyclerItemType getItemType() {
        return type;
    }

    public void setType(IRecyclerItemType type) {
        this.type = type;
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

    public Class<? extends Activity> getTarget() {
        return target;
    }
}
