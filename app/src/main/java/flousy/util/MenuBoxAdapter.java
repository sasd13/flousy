package flousy.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Samir on 20/02/2015.
 */
public class MenuBoxAdapter extends BaseAdapter {

    private Context context;
    private MenuBox menuBox;

    public MenuBoxAdapter(Context context, MenuBox menuBox) {
        this.context = context;
        this.menuBox = menuBox;
    }

    @Override
    public int getCount() {
        return this.menuBox.getMenuItemBoxes().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            linearLayout = (LinearLayout) inflater.inflate(MenuItemBox.DEFAULT_LAYOUT, null);
        }
        else {
            linearLayout = (LinearLayout) convertView;
        }

        this.menuBox.getMenuItemBoxes().get(position).setBox(linearLayout);

        return linearLayout;
    }
}
