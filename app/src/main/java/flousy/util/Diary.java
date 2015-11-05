package flousy.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 19/06/2015.
 */
public class Diary implements Iterable {

    private List<String> list;

    public Diary() {
        this.list = new ArrayList<>();
    }

    public void add(String s) {
        this.list.add(s);
    }

    public void remove(String s) {
        this.list.remove(s);
    }

    public String getLast() {
        if (isEmpty()) {
            return null;
        }

        return this.list.get(size() - 1);
    }

    public String[] getLasts(int size) {
        List l = this.list.subList(Math.max(size() - size, 0), size);

        return (String[]) l.toArray();
    }

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() { return this.list.isEmpty(); }

    public void clear() {
        this.list.clear();
    }

    @Override
    public Iterator iterator() {
        return this.list.iterator();
    }
}
