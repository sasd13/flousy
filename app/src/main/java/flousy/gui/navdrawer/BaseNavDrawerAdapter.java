package flousy.gui.navdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Samir on 22/03/2015.
 */
public class BaseNavDrawerAdapter extends NavDrawerAdapter {

    public BaseNavDrawerAdapter(NavDrawer navDrawer) {
        super(navDrawer);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);

        final NavDrawerItem navDrawerItem = (NavDrawerItem) getItem(position);
        if(navDrawerItem instanceof BaseNavDrawerItem) {
            convertView.setClickable(true);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getNavDrawer().getContext().startActivity(((BaseNavDrawerItem) navDrawerItem).getIntent());
                }
            });
        }

        return convertView;
    }
}
