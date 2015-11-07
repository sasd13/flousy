package flousy.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/**
 * Created by Samir on 19/06/2015.
 */
public abstract class ObservableList<T> extends Observable implements Iterable<T> {

    private List<T> list;

    protected ObservableList() { this.list = new ArrayList<>(); }

    public abstract T get(Object object);

    public void add(T t) {
        boolean added = this.list.add(t);

        if (added) {
            setChanged();
            notifyObservers();
        }
    }

    public void remove(T t) {
        boolean removed = this.list.remove(t);

        if (removed) {
            setChanged();
            notifyObservers();
        }
    }

    public boolean contains(T t) { return this.list.contains(t); }

    public int size() { return this.list.size(); }

    public boolean isEmpty() { return this.list.isEmpty(); }

    private void clear() {
        this.list.clear();

        setChanged();
        notifyObservers();
    }

    public List<T> getList() {
        return this.list;
    }

    @Override
    public Iterator<T> iterator() {
        return this.list.iterator();
    }
}
