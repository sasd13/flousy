package flousy.gui.navdrawer;

import android.view.View;

/**
 * Created by Samir on 22/03/2015.
 */
public abstract class NavDrawerItem {

    private int layoutResource;
    private View view;

    protected NavDrawerItem(int layoutResource) {
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

    public abstract void inflate(View view);
}
