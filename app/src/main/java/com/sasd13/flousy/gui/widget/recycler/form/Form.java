package com.sasd13.flousy.gui.widget.recycler.form;

import android.support.v7.widget.RecyclerView;

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;

import java.util.List;

/**
 * Created by Samir on 29/05/2016.
 */
public class Form extends Recycler {

    private List<FormItemInput> inputs;

    protected Form(RecyclerView recyclerView, int recyclerItemLayout) {
        super(recyclerView, recyclerItemLayout);
    }

    @Override
    public void addItem(RecyclerItem recyclerItem) {
        super.addItem(recyclerItem);
    }

    public List<FormItemInput> getInputs() {
        return inputs;
    }
}
