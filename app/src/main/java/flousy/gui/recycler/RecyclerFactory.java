package flousy.gui.recycler;

import android.content.Context;

import flousy.gui.recycler.drawer.Drawer;
import flousy.gui.recycler.grid.Grid;

/**
 * Created by Samir on 13/03/2015.
 */
public class RecyclerFactory {

    private RecyclerFactory() {}

    public static AbstractRecycler create(RecyclerType type, Context context) {
        switch (type) {
            case DAWER :
                return new Drawer(context);
            case GRID :
                return new Grid(context);
        }

        return null;
    }
}
