package flousy.gui.navdrawer;

import android.content.Context;

/**
 * Created by Samir on 23/03/2015.
 */
public class NavDrawerFactory {

    private NavDrawerFactory() {}

    public static NavDrawer create(Context context, NavDrawerType type) {
        switch (type) {
            case BASEDRAWER :
                return new BaseNavDrawer(context);
        }

        return null;
    }
}
