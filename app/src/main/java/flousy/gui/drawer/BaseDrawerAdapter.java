package flousy.gui.drawer;

import android.view.View;
import android.view.ViewGroup;

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

        if(getItem(position) instanceof BaseDrawerItem) {
            final BaseDrawerItem baseDrawerItem = (BaseDrawerItem) getItem(position);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(baseDrawerItem.getIntent() != null) {
                        baseDrawerItem.getContext().startActivity(baseDrawerItem.getIntent());
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
        }

        return convertView;
    }
}
