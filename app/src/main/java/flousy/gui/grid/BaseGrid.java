package flousy.gui.grid;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by Samir on 13/03/2015.
 */
public class BaseGrid extends Grid {

    public BaseGrid() {
        super();
    }

    @Override
    public void adapt(GridView gridView) {
        BaseGridAdapter gridAdapter = new BaseGridAdapter(this);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.performClick();
            }
        });
    }
}