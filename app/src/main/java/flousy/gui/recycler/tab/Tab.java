package flousy.gui.recycler.tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import flousy.gui.recycler.AbstractRecycler;
import flousy.gui.recycler.AbstractRecyclerItem;

/**
 * Created by Samir on 22/05/2015.
 */
public class Tab extends AbstractRecycler {

    public Tab(Context context) {
        super(context);
    }

    @Override
    public boolean addItem(AbstractRecyclerItem abstractRecyclerItem) {
        if(abstractRecyclerItem instanceof TabItem == false) {
            return false;
        }

        return super.addItem(abstractRecyclerItem);
    }

    @Override
    public void adapt(RecyclerView recyclerView) {

    }
}
