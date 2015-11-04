package flousy.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import flousy.util.FlousyCollection;

/**
 * Created by Samir on 19/06/2015.
 */
public abstract class FlousyList<T> implements FlousyCollection<T> {

    protected List<T> list;

    protected FlousyList() { this.list = new ArrayList<>(); }

    @Override
    public void add(T t) {
        this.list.add(t);
    }

    @Override
    public void remove(T t) {
        this.list.remove(t);
    }

    @Override
    public boolean contains(T t) {
        return this.list.contains(t);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() { return this.list.isEmpty(); }

    public void clear() { this.list.clear(); }

    @Override
    public Iterator iterator() {
        return this.list.iterator();
    }
}
