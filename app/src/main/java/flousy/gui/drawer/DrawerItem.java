package flousy.gui.drawer;

import android.content.Context;
import android.view.View;

/**
 * Created by Samir on 22/03/2015.
 */
public abstract class DrawerItem {

    private Context context;
    private int layoutResource;
    private View view;

    protected DrawerItem(Context context, int layoutResource) {
        this.context = context;
        this.layoutResource = layoutResource;
        this.view = null;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
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
