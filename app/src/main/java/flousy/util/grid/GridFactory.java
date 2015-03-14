package flousy.util.grid;

import android.content.Context;

/**
 * Created by Samir on 11/03/2015.
 */
public class GridFactory {

    public static final int TYPE_BASEGRID = 0;

    private GridFactory() {}

    public static Grid create(Context context, int type) {
        Grid grid = null;

        switch (type) {
            case TYPE_BASEGRID :
                grid = new BaseGrid(context);
                break;
        }

        return grid;
    }
}
