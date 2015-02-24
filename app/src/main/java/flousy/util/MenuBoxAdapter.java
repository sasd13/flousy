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
    public int getCount() { return this.menuBox.getMenuItemBoxes().size(); }

    @Override
    public Object getItem(int position) {
        return this.menuBox.getMenuItemBoxes().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewGroup menuItemBoxContainer;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            menuItemBoxContainer = (LinearLayout) inflater.inflate(this.menuBox.getMenuItemBoxLayoutResource(), null);
        }
        else {
            menuItemBoxContainer = (LinearLayout) convertView;
        }

        this.menuBox.getMenuItemBoxes().get(position).setContainer(menuItemBoxContainer);

        return menuItemBoxContainer;
    }
}