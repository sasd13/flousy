package flousy.gui.navdrawer;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class MenuNavDrawerItem extends BaseNavDrawerItem {

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
    public void inflate(View view) {
        super.inflate(view);

        ViewGroup viewGroup = (ViewGroup) view;

        this.itemView = viewGroup.findViewWithTag("navdraweritem_base_menu_view");
        if(this.itemView != null) {
            this.itemView.setBackgroundColor(this.itemColor);
        }
    }
}
