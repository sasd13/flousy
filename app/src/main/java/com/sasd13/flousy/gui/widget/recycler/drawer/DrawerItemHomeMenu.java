package com.sasd13.flousy.gui.widget.recycler.drawer;

import android.view.View;
import android.view.ViewStub;

import com.sasd13.androidx.gui.widget.recycler.drawer.DrawerItemIntentable;
import com.sasd13.flousy.R;

public class DrawerItemHomeMenu extends DrawerItemIntentable {

    private int color;
    private View colorView;

    public DrawerItemHomeMenu() {
        super(R.layout.draweritemhomemenu);
    }

    public void setColor(int color) {
        this.color = color;

        try {
            this.colorView.setBackgroundColor(this.color);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inflate(ViewStub viewStub) {
        super.inflate(viewStub);

        findViews();
        bindViews();
    }

    private void findViews() {
        this.colorView = getView().findViewById(R.id.draweritemhomemenu_view_color);
    }

    private void bindViews() {
        if (this.color == 0) {
            this.color = getView().getContext().getResources().getColor(R.color.customGreenApp);
        }
        setColor(this.color);
    }
}
