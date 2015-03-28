package flousy.gui.recycler.drawer;

import android.view.View;
import android.view.ViewStub;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class MenuDrawerItem extends DrawerItem {

    private int color;
    private View colorView;

    public MenuDrawerItem() {
        super(R.layout.draweritem_menu);

        this.color = 0;
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
    public View inflate(ViewStub viewStub) {
        viewStub.setLayoutResource(getLayoutResource());
        View view = super.inflate(viewStub);
        if (view == null) {
            return null;
        }

        this.colorView = view.findViewById(R.id.draweritem_menu_colorview);
        this.colorView.setBackgroundColor(this.color);

        return view;
    }
}
