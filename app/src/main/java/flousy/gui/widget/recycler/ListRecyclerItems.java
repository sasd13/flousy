package flousy.gui.widget.recycler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 14/06/2015.
 */
public class ListRecyclerItems implements Iterable {

    private List<RecyclerItem> list;

    public ListRecyclerItems() {
        this.list = new ArrayList<>();
    }

    public RecyclerItem get(int index) {
        return this.list.get(index);
    }

    public boolean add(RecyclerItem recyclerItem) {
        return this.list.add(recyclerItem);
    }

    public boolean remove(RecyclerItem recyclerItem) {
        return this.list.remove(recyclerItem);
    }

    public RecyclerItem remove(int index) {
        return this.list.remove(index);
    }

    public void clear() {
        this.list.clear();
    }

    public int size() {
        return this.list.size();
    }

    @Override
    public Iterator iterator() {
        return this.list.iterator();
    }
}
