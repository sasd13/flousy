package flousy.util;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Samir on 19/06/2015.
 */
public abstract class List<T> implements Iterable<T> {

    protected java.util.List<T> list;

    protected List() { this.list = new ArrayList<>(); }

    public abstract T get(Object object);

    public void add(T t) {
        this.list.add(t);
    }

    public void remove(T t) {
        this.list.remove(t);
    }

    public boolean contains(T t) { return this.list.contains(t); }

    public int size() { return this.list.size(); }

    public boolean isEmpty() { return this.list.isEmpty(); }

    private void clear() {
        this.list.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return this.list.iterator();
    }
}
