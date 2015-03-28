package flousy.gui.recycler;

import flousy.gui.recycler.drawer.Drawer;
import flousy.gui.recycler.grid.Grid;

/**
 * Created by Samir on 13/03/2015.
 */
public class RecyclerFactory {

    private RecyclerFactory() {}

    public static AbstractRecycler create(RecyclerType type) {
        switch (type) {
            case DAWER :
                return Drawer.getInstance();
            case GRID :
                return new Grid();
        }

        return null;
    }
}
