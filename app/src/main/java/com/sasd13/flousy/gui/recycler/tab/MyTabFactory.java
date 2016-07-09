package com.sasd13.flousy.gui.recycler.tab;

import com.sasd13.androidex.gui.widget.IItemType;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItemBuilder;
import com.sasd13.androidex.gui.widget.recycler.tab.TabFactory;
import com.sasd13.flousy.gui.recycler.MyRecyclerItemType;

/**
 * Created by ssaidali2 on 02/07/2016.
 */
public class MyTabFactory extends TabFactory {

    @Override
    public RecyclerItem makeItem(IItemType itemType) {
        if (MyRecyclerItemType.TAB_OPERATION.equals(itemType)) {
            return new OperationItem();
        } else {
            return super.makeItem(itemType);
        }
    }

    @Override
    public RecyclerItemBuilder makeItemBuilder(IItemType itemType) {
        if (MyRecyclerItemType.TAB_OPERATION.equals(itemType)) {
            return new OperationItemBuilder(this);
        } else {
            return super.makeItemBuilder(itemType);
        }
    }
}
