package flousy.gui.navdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Samir on 22/03/2015.
 */
public class NavDrawerAdapter extends BaseAdapter {

    private NavDrawer navDrawer;

    public NavDrawerAdapter(NavDrawer navDrawer) {
        this.navDrawer = navDrawer;
    }

    @Override
    public int getCount() {
        return this.navDrawer.count();
    }

    @Override
    public Object getItem(int position) {
        return this.navDrawer.getChild(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AbstractNavDrawerItem drawerItem = this.navDrawer.getChild(position);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) drawerItem.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(drawerItem.getItemLayoutResource(), null);
        }

        drawerItem.setView(convertView);
        drawerItem.inflate();

        return convertView;
    }
}
