package com.sasd13.flousy.gui.recycler.tab;

import com.sasd13.androidex.gui.widget.IType;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.flousy.gui.recycler.MyRecyclerFactoryType;

/**
 * Created by ssaidali2 on 02/07/2016.
 */
public class MyTabFactory extends RecyclerFactory {

    @Override
    public RecyclerItem makeItem(IType type) {
        if (MyRecyclerFactoryType.TAB_MYTAB.equals(type)) {
            return new OperationItem();
        } else {
            return super.makeItem(type);
        }
    }
}
