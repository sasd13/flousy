package com.sasd13.flousy.content.form;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;

/**
 * Created by ssaidali2 on 02/07/2016.
 */
public abstract class Form {

    protected RecyclerHolder holder;

    public Form() {
        holder = new RecyclerHolder();
    }

    public RecyclerHolder getHolder() {
        return holder;
    }
}
