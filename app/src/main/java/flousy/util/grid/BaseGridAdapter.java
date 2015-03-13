package flousy.util.grid;

import android.view.View;
import android.view.ViewGroup;

import flousy.util.widget.CustomOnTouchListener;

/**
 * Created by Samir on 20/02/2015.
 */
public class BaseGridAdapter extends GridAdapter {

    public BaseGridAdapter(BaseGrid grid) {
        super(grid);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View containerView = super.getView(position, convertView, parent);

        BaseGridItem gridItem = (BaseGridItem) this.getItem(position);
        containerView.setOnTouchListener(new CustomOnTouchListener(gridItem.getColor()));

        return containerView;
    }
}