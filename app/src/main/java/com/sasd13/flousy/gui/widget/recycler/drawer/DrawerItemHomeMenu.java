package com.sasd13.flousy.gui.widget.recycler.drawer;

import android.view.View;
import android.view.ViewStub;

import com.sasd13.androidex.gui.widget.recycler.drawer.DrawerItemIntentable;
import com.sasd13.flousy.R;

public class DrawerItemHomeMenu extends DrawerItemIntentable {

    private int color;
    private View colorView;

    public DrawerItemHomeMenu() {
        super(R.layout.draweritem_menu);
    }

    public void setColor(int color) {
        this.color = color;

        try {
            colorView.setBackgroundColor(color);
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
        colorView = view.findViewById(R.id.draweritemhomemenu_view_color);
    }

    private void bindViews() {
        if (color == 0) {
            color = view.getContext().getResources().getColor(R.color.green);
        }
        setColor(color);
    }
}
