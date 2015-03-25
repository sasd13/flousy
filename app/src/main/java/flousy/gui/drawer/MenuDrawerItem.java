package flousy.gui.drawer;

import android.content.Intent;
import android.view.View;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class MenuDrawerItem extends DrawerItem {

    private int color;
    private View colorView;

    public MenuDrawerItem(CharSequence text, Intent intent, int color) {
        super(R.layout.draweritem_menu, text, intent);

        this.color = color;
        this.colorView = null;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;

        if(this.colorView != null) {
            this.colorView.setBackgroundColor(this.color);
        }
    }

    @Override
    public void parse(View view) {
        if(this.colorView == null) {
            super.parse(view);
            this.colorView = view.findViewById(R.id.draweritem_menu_colorview);
        }

        this.colorView.setBackgroundColor(this.color);
    }
}
