package com.sasd13.flousy.gui.widget.recycler.form;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samir on 29/05/2016.
 */
public class Form extends Recycler {

    private List<FormItemInput> inputs;

    public Form(RecyclerView recyclerView, int recyclerItemLayout) {
        super(recyclerView, recyclerItemLayout);

        inputs = new ArrayList<>();
    }

    @Override
    protected void setLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    @Override
    public void addItem(RecyclerItem recyclerItem) {
        super.addItem(recyclerItem);

        inputs.add(((FormItem) recyclerItem).getInput());
    }

    @Override
    public void removeItem(RecyclerItem recyclerItem) {
        super.removeItem(recyclerItem);

        inputs.add(((FormItem) recyclerItem).getInput());
    }

    @Override
    public void clearItems() {
        super.clearItems();

        inputs.clear();
    }

    public List<FormItemInput> getInputs() {
        return inputs;
    }
}
