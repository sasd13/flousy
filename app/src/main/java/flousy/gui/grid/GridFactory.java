package flousy.gui.grid;

/**
 * Created by Samir on 13/03/2015.
 */
public class GridFactory {

    private GridFactory() {}

    public static Grid create(GridType type) {
        switch (type) {
            case BASEGRID :
                return new BaseGrid();
        }

        return null;
    }
}
