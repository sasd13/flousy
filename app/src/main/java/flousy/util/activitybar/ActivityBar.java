package flousy.util.activitybar;

import android.content.Context;
import android.view.View;
import android.view.ViewStub;

/**
 * Created by Samir on 11/03/2015.
 */
public abstract class ActivityBar {

    private int layoutResource;
    private View containerView;

    protected ActivityBar(int layoutResource) {
        this.layoutResource = layoutResource;
        this.containerView = null;
    }

    public int getLayoutResource() {
        return this.layoutResource;
    }

    public void setLayoutResource(int layoutResource) {
        this.layoutResource = layoutResource;
    }

    public View getContainerView() {
        return this.containerView;
    }

    public void setContainerView(View containerView) {
        this.containerView = containerView;
    }

    public abstract void inflate(ViewStub viewStub);
}
