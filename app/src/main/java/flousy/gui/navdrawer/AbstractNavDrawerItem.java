package flousy.gui.navdrawer;

import android.view.View;

/**
 * Created by Samir on 22/03/2015.
 */
public abstract class AbstractNavDrawerItem {

    private int itemLayoutResource;
    private View view;

    protected AbstractNavDrawerItem(int itemLayoutResource) {
        this.itemLayoutResource = itemLayoutResource;
        this.view = null;
    }

    public int getItemLayoutResource() {
        return this.itemLayoutResource;
    }

    public void setItemLayoutResource(int itemLayoutResource) {
        this.itemLayoutResource = itemLayoutResource;
    }

    public View getView() {
        return this.view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public abstract View inflate();
}
