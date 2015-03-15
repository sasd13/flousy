package flousy.gui.grid;

import android.view.View;
import android.view.ViewGroup;

import flousy.gui.listener.CustomOnTouchListener;

/**
 * Created by Samir on 13/03/2015.
 */
public class BaseGridAdapter extends GridAdapter {

    public BaseGridAdapter(BaseGrid grid) {
        super(grid);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);

        BaseGridItem gridItem = (BaseGridItem) getItem(position);
        convertView.setOnTouchListener(new CustomOnTouchListener(gridItem.getColor()));

        return convertView;
    }
}