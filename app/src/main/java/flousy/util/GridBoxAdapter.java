package flousy.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Samir on 20/02/2015.
 */
public class GridBoxAdapter extends BaseAdapter {

    private Context context;
    private GridBox gridBox;

    public GridBoxAdapter(Context context, GridBox gridBox) {
        this.context = context;
        this.gridBox = gridBox;
    }

    @Override
    public int getCount() { return this.gridBox.getGridItemBoxes().size(); }

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
        ViewGroup gridItemBoxContainer;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridItemBoxContainer = (ViewGroup) inflater.inflate(this.gridBox.getGridItemBoxLayoutResource(), null);
        }
        else {
            gridItemBoxContainer = (ViewGroup) convertView;
        }

        this.gridBox.getGridItemBoxes().get(position).setContainer(gridItemBoxContainer);

        return gridItemBoxContainer;
    }
}