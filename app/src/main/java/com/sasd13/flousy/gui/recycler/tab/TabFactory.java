package com.sasd13.flousy.gui.recycler.tab;

import android.content.Context;

import com.sasd13.androidex.gui.widget.IItemType;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItemBuilder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerModel;
import com.sasd13.flousy.gui.recycler.RecyclerItemType;

/**
 * Created by ssaidali2 on 02/07/2016.
 */
public class TabFactory extends com.sasd13.androidex.gui.widget.recycler.tab.TabFactory {

    public TabFactory(Context context) {
        super(context);
    }

    @Override
    public RecyclerModel makeModel(IItemType itemType) {
        if (RecyclerItemType.TAB_OPERATION.equals(itemType)) {
            return new OperationModel();
        } else {
            return super.makeModel(itemType);
        }
    }

    @Override
    public RecyclerItem makeItem(IItemType itemType) {
        if (RecyclerItemType.TAB_OPERATION.equals(itemType)) {
            return new OperationItem();
        } else {
            return super.makeItem(itemType);
        }
    }

    @Override
    public RecyclerItemBuilder makeItemBuilder(IItemType itemType) {
        if (RecyclerItemType.TAB_OPERATION.equals(itemType)) {
            return new OperationItemBuilder(this);
        } else {
            return super.makeItemBuilder(itemType);
        }
    }
}
