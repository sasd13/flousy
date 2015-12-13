package com.sasd13.flousy.gui.widget.recycler;

import android.view.View;
import android.view.ViewStub;

public abstract class RecyclerItem {

    private int layoutResource;
    private View view;

    protected RecyclerItem(int layoutResource) {
        this.layoutResource = layoutResource;
    }

    public View getView() {
        return this.view;
    }

    public void inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(this.layoutResource);
        this.view = viewStub.inflate();
    }
}