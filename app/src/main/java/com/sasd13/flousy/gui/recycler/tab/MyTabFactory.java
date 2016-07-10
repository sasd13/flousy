package com.sasd13.flousy.gui.recycler.tab;

import com.sasd13.androidex.gui.widget.IType;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.tab.TabFactory;
import com.sasd13.flousy.gui.recycler.MyRecyclerFactoryType;

/**
 * Created by ssaidali2 on 02/07/2016.
 */
public class MyTabFactory extends TabFactory {

    @Override
    public RecyclerItem makeItem(IType type) {
        if (MyRecyclerFactoryType.TAB_OPERATION.equals(type)) {
            return new OperationItem();
        } else {
            return super.makeItem(type);
        }
    }
}
