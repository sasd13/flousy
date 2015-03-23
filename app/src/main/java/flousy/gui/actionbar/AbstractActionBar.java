package flousy.gui.actionbar;

import android.view.View;
import android.view.ViewStub;

/**
 * Created by Samir on 19/03/2015.
 */
public abstract class AbstractActionBar {

    private int layoutResource;
    private View view;

    protected AbstractActionBar(int layoutResource) {
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

    public abstract void inflate(ViewStub viewStub);
}
