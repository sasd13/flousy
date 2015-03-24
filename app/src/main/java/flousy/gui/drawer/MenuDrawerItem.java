package flousy.gui.drawer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class MenuDrawerItem extends BaseDrawerItem {

    private int color;
    private View colorView;

    public MenuDrawerItem(Context context, CharSequence text, Intent intent, int color) {
        super(context, text, intent, R.layout.draweritem_base_menu);

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
    public void inflate(View view) {
        super.inflate(view);

        ViewGroup viewGroup = (ViewGroup) view;

        this.colorView = viewGroup.findViewById(R.id.draweritem_base_menu_colorview);
        if(this.colorView != null) {
            this.colorView.setBackgroundColor(this.color);
        }
    }
}
