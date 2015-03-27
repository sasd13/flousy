package flousy.gui.grid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Samir on 20/02/2015.
 */
public class GridAdapter extends BaseAdapter {

    private ArrayList<AbstractGridItem> listAbstractGridItem;
    private int itemLayout;

    protected GridAdapter(ArrayList<AbstractGridItem> listAbstractGridItem, int itemLayout) {
        this.listAbstractGridItem = listAbstractGridItem;
        this.itemLayout = itemLayout;
    }

    @Override
    public int getCount() { return this.listAbstractGridItem.size(); }

    @Override
    public AbstractGridItem getItem(int position) {
        return this.listAbstractGridItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AbstractGridItem abstractGridItem = this.listAbstractGridItem.get(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(abstractGridItem.getLayoutResource(), parent, false);
        }

        abstractGridItem.parse(convertView, parent.getContext());

        return convertView;
    }
}