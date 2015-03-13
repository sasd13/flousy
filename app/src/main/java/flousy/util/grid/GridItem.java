package flousy.util.grid;

import android.view.View;

public abstract class GridItem {

    /**
     * GridItemBox parent layout (container)
     */
    private View containerView;

    /**
     * Default constructor
     */
    protected GridItem() {
        this.containerView = null;
    }

    /**
     * Get containerView
     *
     * @return ViewGroup
     */
    public View getContainerView() {
        return this.containerView;
    }

    /**
     * Set containerView
     *
     * @param containerView
     */
    public void setContainerView(View containerView) {
        this.containerView = containerView;
    }

    public abstract void inflate();
}