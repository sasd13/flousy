package flousy.util.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.GridView;

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
        return this.gridBox.getGridItemBoxes().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewGroup containerView;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            containerView = (ViewGroup) inflater.inflate(this.gridBox.getGridItemBoxLayoutResource(), null);
        }
        else {
            containerView = (ViewGroup) convertView;
        }

        GridItemBox gridItemBox = this.gridBox.getGridItemBoxes().get(position);
        containerView.setOnTouchListener(new CustomOnTouchListener(gridItemBox.getColor()));
        containerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GridView gridView = (GridView) view.getParent();
                gridView.performItemClick(view, gridView.getPositionForView(view), 0);
            }
        });
        gridItemBox.setContainerView(containerView);

        return containerView;
    }
}