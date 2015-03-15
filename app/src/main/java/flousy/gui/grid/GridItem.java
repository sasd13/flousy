package flousy.gui.grid;

import android.view.View;

/**
 * Created by Samir on 13/03/2015.
 */
public abstract class GridItem {

    /**
     * GridItem view
     */
    private View view;

    /**
     * Default constructor
     */
    protected GridItem() {
        this.view = null;
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
    public void setView(View view) {
        this.view = view;
    }

    public abstract void inflate();
}