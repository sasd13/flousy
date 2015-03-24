package flousy.gui.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Samir on 22/03/2015.
 */
public class DrawerAdapter extends BaseAdapter {

    private Drawer drawer;

    public DrawerAdapter(Drawer drawer) {
        this.drawer = drawer;
    }

    @Override
    public int getCount() {
        return this.drawer.count();
    }

    @Override
    public Object getItem(int position) {
        return this.drawer.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DrawerItem drawerItem = this.drawer.getItem(position);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) drawerItem.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(drawerItem.getLayoutResource(), null);
        }

        drawerItem.inflate(convertView);

        return convertView;
    }
}
