package com.sasd13.flousy.view.gui.browser;

import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.androidex.gui.widget.recycler.drawer.EnumDrawerItemType;

/**
 * Created by ssaidali2 on 19/11/2016.
 */

public enum EnumBrowserItemType {
    GRID_ACCOUNT("GRID_ACCOUNT", EnumDrawerItemType.NAV),
    GRID_SETTINGS("GRID_SETTINGS", EnumDrawerItemType.NAV),
    NAV_ACCOUNT("NAV_ACCOUNT", EnumDrawerItemType.NAV),
    NAV_SETTINGS("NAV_SETTINGS", EnumDrawerItemType.NAV),
    NAV_LOGOUT("NAV_LOGOUT", EnumDrawerItemType.NAV),
    ;

    private String code;
    private IRecyclerItemType recyclerItemType;

    private EnumBrowserItemType(String code, IRecyclerItemType recyclerItemType) {
        this.code = code;
        this.recyclerItemType = recyclerItemType;
    }

    public String getCode() {
        return code;
    }

    public IRecyclerItemType getRecyclerItemType() {
        return recyclerItemType;
    }

    public static EnumBrowserItemType find(String code) {
        for (EnumBrowserItemType browser : values()) {
            if (browser.code.equalsIgnoreCase(code)) {
                return browser;
            }
        }

        return null;
    }
}
