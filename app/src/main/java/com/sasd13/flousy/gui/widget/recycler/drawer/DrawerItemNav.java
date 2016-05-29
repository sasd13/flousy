package com.sasd13.flousy.gui.widget.recycler.drawer;

import android.support.v4.content.ContextCompat;
import android.view.View;

import com.sasd13.androidex.gui.widget.recycler.drawer.DrawerItem;
import com.sasd13.flousy.R;

public class DrawerItemNav extends DrawerItem {

    private int color;
    private View colorView;

    public void setColor(int color) {
        this.color = color;

        if (colorView != null) {
            colorView.setBackgroundColor(color);
        }
    }

    @Override
    public void bindView(View view) {
        super.bindView(view);

        findItemViews();
        bindItemViews();
    }

    private void findItemViews() {
        colorView = view.findViewById(R.id.draweritem_nav_colorview);
    }

    private void bindItemViews() {
        if (color == 0) {
            color = ContextCompat.getColor(view.getContext(), R.color.green);
        }
        setColor(color);
    }
}
