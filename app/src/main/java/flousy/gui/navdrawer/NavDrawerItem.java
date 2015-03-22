package flousy.gui.navdrawer;

import android.content.Context;
import android.view.View;

/**
 * Created by Samir on 22/03/2015.
 */
public abstract class NavDrawerItem {

    private Context context;
    private int itemLayoutResource;
    private View view;

    protected NavDrawerItem(Context context, int itemLayoutResource) {
        this.context = context;
        this.itemLayoutResource = itemLayoutResource;
        this.view = null;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
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
