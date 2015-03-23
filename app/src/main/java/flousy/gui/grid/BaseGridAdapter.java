package flousy.gui.grid;

import android.view.View;
import android.view.ViewGroup;

import flousy.gui.listener.CustomOnTouchListener;

/**
 * Created by Samir on 13/03/2015.
 */
public class BaseGridAdapter extends GridAdapter {

    public BaseGridAdapter(BaseGrid baseGrid) {
        super(baseGrid);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);

        BaseGridItem baseGridItem = (BaseGridItem) getItem(position);
        convertView.setOnTouchListener(new CustomOnTouchListener(baseGridItem.getColor()));

        return convertView;
    }
}