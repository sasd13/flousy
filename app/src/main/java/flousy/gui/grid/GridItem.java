package flousy.gui.grid;

import android.content.Context;
import android.view.View;

/**
 * Created by Samir on 13/03/2015.
 */
public abstract class GridItem {

    private Context context;
    private int layoutResource;

    /**
     * GridItem view
     */
    private View view;

    /**
     * Default constructor
     */
    protected GridItem(Context context, int layoutResource) {
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

    /**
     * Get view
     *
     * @return View
     */
    public View getView() {
        return this.view;
    }

    /**
     * Set view
     *
     * @param view
     */
    protected void setView(View view) {
        this.view = view;
    }

    public abstract void inflate(View view);
}