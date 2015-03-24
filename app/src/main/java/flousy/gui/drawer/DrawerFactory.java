package flousy.gui.drawer;

/**
 * Created by Samir on 23/03/2015.
 */
public class DrawerFactory {

    private DrawerFactory() {}

    public static Drawer create(DrawerType type) {
        switch (type) {
            case BASEDRAWER :
                return new BaseDrawer();
        }

        return null;
    }
}
