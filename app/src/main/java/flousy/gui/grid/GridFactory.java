package flousy.gui.grid;

import android.content.Context;

/**
 * Created by Samir on 13/03/2015.
 */
public class GridFactory {

    private GridFactory() {}

    public static Grid create(Context context, GridType type) {
        switch (type) {
            case BASEGRID :
                return new BaseGrid(context);
        }

        return null;
    }
}
