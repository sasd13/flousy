package com.sasd13.flousy.gui.widget.recycler.form;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sasd13.androidex.gui.widget.recycler.Recycler;

/**
 * Created by Samir on 29/05/2016.
 */
public class Form extends Recycler<FormItem> {

    private boolean scrollingDisabled;

    public Form(RecyclerView recyclerView, int recyclerItemLayout) {
        super(recyclerView, recyclerItemLayout);
    }

    @Override
    protected void setLayoutManager() {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return !scrollingDisabled && super.canScrollVertically();
            }
        });
    }

    public void setScrollingDisabled(boolean scrollingDisabled) {
        this.scrollingDisabled = scrollingDisabled;
    }

    public FormItem findItemById(int id) {
        for (FormItem formItem : getItems()) {
            if (formItem.getId() == id) {
                return formItem;
            }
        }

        return null;
    }
}
