package com.sasd13.flousy.gui.widget.recycler.form;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sasd13.androidex.gui.widget.recycler.Recycler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samir on 29/05/2016.
 */
public class Form extends Recycler<FormItem> {

    private List<FormItemInput> inputs;

    public Form(RecyclerView recyclerView, int recyclerItemLayout) {
        super(recyclerView, recyclerItemLayout);

        inputs = new ArrayList<>();
    }

    @Override
    protected void setLayoutManager() {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    @Override
    public void addItem(FormItem formItem) {
        super.addItem(formItem);

        inputs.add(formItem.getInput());
    }

    @Override
    public void removeItem(FormItem formItem) {
        super.removeItem(formItem);

        inputs.remove(formItem.getInput());
    }

    @Override
    public void clearItems() {
        super.clearItems();

        inputs.clear();
    }

    public List<FormItemInput> getInputs() {
        return inputs;
    }

    public FormItemInput findInputById(int id) {
        for (FormItemInput input : inputs) {
            if (input.getId() == id) {
                return input;
            }
        }

        return null;
    }
}
