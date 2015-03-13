package flousy.util.grid;

import android.content.Context;
import android.widget.GridView;

/**
 * Created by Samir on 11/03/2015.
 */
public class GridFactory {

    public static final int TYPE_BASICGRID = 0;
    public static final int GRIDBOX_NEWACTIVITY = 1;

    public GridFactory() { }

    public Grid create(Context context, int boxType, GridView gridView) {
        Grid grid = null;

        switch (boxType) {
            case TYPE_BASICGRID :
                grid = new BaseGrid(context);
                break;
            case GRIDBOX_NEWACTIVITY :
                grid = new BaseGrid(context);
                break;
        }

        grid.create(gridView);

        return grid;
    }
}
