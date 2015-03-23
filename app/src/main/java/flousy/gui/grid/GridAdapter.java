package flousy.gui.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * Created by Samir on 20/02/2015.
 */
public class GridAdapter extends BaseAdapter {

    private Grid grid;

    protected GridAdapter(Grid grid) {
        this.grid = grid;
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void setGrid(Grid grid) {
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
            LayoutInflater inflater = (LayoutInflater) this.grid.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(gridItem.getLayoutResource(), null);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GridView gridView = (GridView) view.getParent();
                gridView.performItemClick(view, gridView.getPositionForView(view), 0);
            }
        });
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.performClick();
                return false;
            }
        });

        gridItem.inflate(convertView);

        return convertView;
    }
}