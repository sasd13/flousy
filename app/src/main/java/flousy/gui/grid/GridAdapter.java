package flousy.gui.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Samir on 20/02/2015.
 */
public class GridAdapter extends BaseAdapter {

    private Grid grid;

    protected GridAdapter(Grid grid) {
        this.grid = grid;
    }

    @Override
    public int getCount() { return this.grid.count(); }

    @Override
    public GridItem getItem(int position) {
        return this.grid.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridItem gridItem = this.grid.getItem(position);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) gridItem.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(gridItem.getLayoutResource(), null);
        }

        gridItem.inflate(convertView);

        return convertView;
    }
}