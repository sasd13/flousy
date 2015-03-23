package flousy.gui.grid;

import android.view.View;

/**
 * Created by Samir on 13/03/2015.
 */
public abstract class GridItem {

    private int layoutResource;

    /**
     * GridItem view
     */
    private View view;

    /**
     * Default constructor
     */
    protected GridItem(int layoutResource) {
        this.layoutResource = layoutResource;
        this.view = null;
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