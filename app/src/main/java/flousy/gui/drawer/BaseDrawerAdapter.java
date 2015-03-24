package flousy.gui.drawer;

import android.view.View;
import android.view.ViewGroup;

import com.diderot.android.flousy.R;

import flousy.gui.listener.CustomOnTouchListener;

/**
 * Created by Samir on 22/03/2015.
 */
public class BaseDrawerAdapter extends DrawerAdapter {

    public BaseDrawerAdapter(Drawer drawer) {
        super(drawer);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);

        final DrawerItem drawerItem = (DrawerItem) getItem(position);

        if(drawerItem instanceof BaseDrawerItem) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(((BaseDrawerItem) drawerItem).getIntent() != null) {
                        ((BaseDrawerItem) drawerItem).getContext().startActivity(((BaseDrawerItem) drawerItem).getIntent());
                    }
                }
            });
            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    view.performClick();
                    return false;
                }
            });
            int color = drawerItem.getContext().getResources().getColor(R.color.background_material_light);
            convertView.setOnTouchListener(new CustomOnTouchListener(color));
        } else if(drawerItem instanceof BaseDrawerItemTitle) {
            convertView.setEnabled(false);
            convertView.setClickable(false);
        }

        return convertView;
    }
}
