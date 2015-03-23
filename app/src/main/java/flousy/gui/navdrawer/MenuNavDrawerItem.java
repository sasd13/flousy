package flousy.gui.navdrawer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class MenuNavDrawerItem extends NavDrawerItem {

    private int itemColor;
    private View itemView;

    public MenuNavDrawerItem(CharSequence itemText, Intent intent, int color) {
        super(itemText, intent, R.layout.navdraweritem_base_menu);

        this.itemColor = color;
        this.itemView = null;
    }

    public int getItemColor() {
        return this.itemColor;
    }

    public void setItemColor(int color) {
        this.itemColor = color;

        if(this.itemView != null) {
            this.itemView.setBackgroundColor(this.itemColor);
        }
    }

    @Override
    public View inflate() {
        ViewGroup viewGroup = (ViewGroup) super.inflate();

        this.itemView = viewGroup.findViewWithTag("navdraweritem_base_menu_view");
        if(this.itemView != null) {
            this.itemView.setBackgroundColor(this.itemColor);
        }

        return viewGroup;
    }
}
