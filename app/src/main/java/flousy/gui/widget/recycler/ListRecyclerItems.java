package flousy.gui.widget.recycler;

import flousy.util.List;

/**
 * Created by Samir on 14/06/2015.
 */
public class ListRecyclerItems extends List<RecyclerItem> {

    @Override
    public RecyclerItem get(Object index) {
        return getList().get((int) index);
    }
}
