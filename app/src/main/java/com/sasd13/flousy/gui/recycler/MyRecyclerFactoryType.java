package com.sasd13.flousy.gui.recycler;

import com.sasd13.androidex.gui.widget.IType;
import com.sasd13.flousy.gui.recycler.tab.MyTabFactory;

/**
 * Created by ssaidali2 on 09/07/2016.
 */
public enum MyRecyclerFactoryType implements IType {

    TAB_OPERATION("TAB", "OPERATION", MyTabFactory.class),
    ;

    private String code, label;
    private Class target;

    private MyRecyclerFactoryType(String code, String label, Class target) {
        this.code = code;
        this.label = label;
        this.target = target;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public Class getTarget() {
        return target;
    }

    public static MyRecyclerFactoryType find(String label) {
        for (MyRecyclerFactoryType type : values()) {
            if (type.label.equalsIgnoreCase(label)) {
                return type;
            }
        }

        return null;
    }
}
