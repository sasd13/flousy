package flousy.gui.recycler;

import android.view.View;
import android.view.ViewStub;

/**
 * Created by Samir on 22/03/2015.
 */
public abstract class AbstractRecyclerItem {

    private int layoutResource;
    private View view;

    protected AbstractRecyclerItem(int layoutResource) {
        this.layoutResource = layoutResource;
        this.view = null;
    }

    public int getLayoutResource() {
        return this.layoutResource;
    }

    public void setLayoutResource(int layoutResource) {
        this.layoutResource = layoutResource;
    }

    public View getView() {
        return this.view;
    }

    protected void setView(View view) {
        this.view = view;
    }

    public abstract View inflate(ViewStub viewStub);
}
