package flousy.util.activitybar;

import android.view.View;
import android.view.ViewStub;

/**
 * Created by Samir on 11/03/2015.
 */
public abstract class ActivityBar {

    private int layoutResource;

    protected ActivityBar(int layoutResource) {
        this.layoutResource = layoutResource;
    }

    public int getLayoutResource() {
        return this.layoutResource;
    }

    public void setLayoutResource(int layoutResource) {
        this.layoutResource = layoutResource;
    }

    public abstract View create(ViewStub viewStub);
}
