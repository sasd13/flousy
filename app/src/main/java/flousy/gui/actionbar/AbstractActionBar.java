package flousy.gui.actionbar;

import android.content.Context;
import android.view.View;
import android.view.ViewStub;

/**
 * Created by Samir on 19/03/2015.
 */
public abstract class AbstractActionBar {

    private View view;

    protected AbstractActionBar() {
        this.view = null;
    }

    public View getView() {
        return this.view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public abstract void inflate(ViewStub viewStub);
}
