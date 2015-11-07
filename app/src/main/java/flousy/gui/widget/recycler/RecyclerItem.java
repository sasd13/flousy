package flousy.gui.widget.recycler;

import android.view.View;
import android.view.ViewStub;

/**
 * Created by Samir on 22/03/2015.
 */
public abstract class RecyclerItem {

    private int layoutResource;
    private View view;

    protected RecyclerItem(int layoutResource) {
        this.layoutResource = layoutResource;
    }

    public void inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(this.layoutResource);
        this.view = viewStub.inflate();
    }

    public View getView() {
        return this.view;
    }
}