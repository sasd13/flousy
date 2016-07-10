package com.sasd13.flousy.gui.recycler;

import com.sasd13.androidex.gui.widget.IType;
import com.sasd13.androidex.gui.widget.recycler.IRecyclerFactory;
import com.sasd13.flousy.gui.recycler.tab.MyTabFactory;

/**
 * Created by ssaidali2 on 09/07/2016.
 */
public enum MyRecyclerFactoryType implements IType {

    TAB_MYTAB("TAB", "MYTAB", MyTabFactory.class),
    ;

    private String code, label;
    private Class<? extends IRecyclerFactory> target;

    private MyRecyclerFactoryType(String code, String label, Class<? extends IRecyclerFactory> target) {
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

    public Class<? extends IRecyclerFactory> getTarget() {
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
