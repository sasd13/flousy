package flousy.gui.activitybar;

import android.view.View;
import android.view.ViewStub;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 11/03/2015.
 */
public abstract class ActivityBar {

    protected int layoutResource, color;
    protected View view;

    protected ActivityBar(int layoutResource) {
        this.layoutResource = layoutResource;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;

        try {
            this.view.setBackgroundColor(this.color);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(this.layoutResource);
        this.view = viewStub.inflate();

        if (this.color == 0) {
            this.color = this.view.getContext().getResources().getColor(R.color.customGreenApp);
        }
        this.view.setBackgroundColor(this.color);
    }
}
