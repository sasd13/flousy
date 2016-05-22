package com.sasd13.flousy.gui.widget.recycler.drawer;

import android.view.View;

import com.sasd13.androidex.gui.widget.recycler.drawer.DrawerItem;
import com.sasd13.flousy.R;

public class DrawerItemNav extends DrawerItem {

    private int color;
    private View colorView;

    public void setColor(int color) {
        this.color = color;

        try {
            colorView.setBackgroundColor(color);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setView(View view) {
        super.setView(view);

        findViews();
        bindViews();
    }

    private void findViews() {
        colorView = view.findViewById(R.id.draweritem_nav_colorview);
    }

    private void bindViews() {
        if (color == 0) {
            color = view.getContext().getResources().getColor(R.color.green);
        }
        setColor(color);
    }
}
