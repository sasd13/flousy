package com.sasd13.flousy.gui.recycler;

import com.sasd13.androidex.gui.widget.IItemType;

/**
 * Created by ssaidali2 on 02/07/2016.
 */
public enum RecyclerItemType implements IItemType {

    TAB_OPERATION("TAB_OPERATION"),
    ;

    private String code;

    private RecyclerItemType(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    public static RecyclerItemType find(String code) {
        for (RecyclerItemType recyclerItemType : values()) {
            if (recyclerItemType.getCode().equalsIgnoreCase(code)) {
                return recyclerItemType;
            }
        }

        return null;
    }
}
