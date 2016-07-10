package com.sasd13.flousy.gui.recycler;

import com.sasd13.androidex.gui.widget.IType;

/**
 * Created by ssaidali2 on 09/07/2016.
 */
public enum MyRecyclerFactoryType implements IType {

    TAB_OPERATION("TAB", "OPERATION"),
    ;

    private String code, label;

    private MyRecyclerFactoryType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public static MyRecyclerFactoryType find(String label) {
        for (MyRecyclerFactoryType myRecyclerFactoryType : values()) {
            if (myRecyclerFactoryType.label.equalsIgnoreCase(label)) {
                return myRecyclerFactoryType;
            }
        }

        return null;
    }
}
