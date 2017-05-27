package com.sasd13.flousy.view.gui.tab;

import com.sasd13.androidex.gui.widget.recycler.IRecyclerItemType;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItemBuilder;

/**
 * Created by ssaidali2 on 02/07/2016.
 */
public enum EnumTabItemType implements IRecyclerItemType {
    OPERATION("OPERATIONITEM", OperationItem.class, RecyclerItemBuilder.class),
    ;

    private String code;
    private Class<? extends RecyclerItem> recyclerItemClass;
    private Class<? extends RecyclerItemBuilder> recyclerItemBuilderClass;

    private EnumTabItemType(String code, Class<? extends RecyclerItem> recyclerItemClass, Class<? extends RecyclerItemBuilder> recyclerItemBuilderClass) {
        this.code = code;
        this.recyclerItemClass = recyclerItemClass;
        this.recyclerItemBuilderClass = recyclerItemBuilderClass;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Class<? extends RecyclerItem> getRecyclerItemClass() {
        return recyclerItemClass;
    }

    @Override
    public Class<? extends RecyclerItemBuilder> getRecyclerItemBuilderClass() {
        return recyclerItemBuilderClass;
    }

    public static EnumTabItemType find(String code) {
        for (EnumTabItemType type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }

        return null;
    }
}
