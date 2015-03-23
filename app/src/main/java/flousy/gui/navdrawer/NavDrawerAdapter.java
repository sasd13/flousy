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

    public NavDrawer getNavDrawer() {
        return this.navDrawer;
    }

    public void setNavDrawer(NavDrawer navDrawer) {
        this.navDrawer = navDrawer;
    }

    @Override
    public int getCount() {
        return this.navDrawer.count();
    }

    @Override
    public Object getItem(int position) {
        return this.navDrawer.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NavDrawerItem drawerItem = this.navDrawer.getItem(position);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.navDrawer.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(drawerItem.getLayoutResource(), null);
        }

        drawerItem.inflate(convertView);

        return convertView;
    }
}
