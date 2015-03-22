package flousy.gui.grid;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.diderot.android.flousy.R;

/**
 * Created by Samir on 13/03/2015.
 */
public class BaseGrid extends Grid {

    public BaseGrid(Context context) {
        super(context, R.layout.griditem_base);
    }

    @Override
    public void adapt(GridView gridView) {
        BaseGridAdapter gridAdapter = new BaseGridAdapter(this);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseGridItem baseGridItem = (BaseGridItem) parent.getItemAtPosition(position);
                getContext().startActivity(baseGridItem.getIntent());
            }
        });
    }
}