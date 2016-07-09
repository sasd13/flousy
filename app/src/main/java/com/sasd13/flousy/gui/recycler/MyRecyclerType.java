package com.sasd13.flousy.gui.recycler;

import com.sasd13.androidex.gui.widget.IType;

/**
 * Created by ssaidali2 on 09/07/2016.
 */
public enum MyRecyclerType implements IType {

    TAB_OPERATION("TAB", "OPERATION"),
    ;

    private String code, label;

    private MyRecyclerType(String code, String label) {
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

    public static MyRecyclerType find(String label) {
        for (MyRecyclerType myRecyclerType : values()) {
            if (myRecyclerType.label.equalsIgnoreCase(label)) {
                return myRecyclerType;
            }
        }

        return null;
    }
}
