package flousy.gui.widget.recycler.drawer;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 22/03/2015.
 */
public class DrawerItemTitle extends DrawerItem {

    public DrawerItemTitle() {
        super(R.layout.draweritem_title);
    }

    @Override
    public void setText(CharSequence text) {
        super.setText(text.toString().toUpperCase());
    }
}
