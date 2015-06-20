package flousy.content.spend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListSpends implements Iterable {

    private List<Spend> list;

    public ListSpends() {
        this.list = new ArrayList<>();
    }

    public boolean add(Spend income) {
        return this.list.add(income);
    }

    public boolean remove(Spend income) {
        return this.list.remove(income);
    }

    public Spend remove(int index) {
        return this.list.remove(index);
    }

    public Spend get(int index) {
        return this.list.get(index);
    }

    public boolean contains(Spend income) {
        for (Spend income2 : this.list) {
            if (income2.equals(income)) {
                return true;
            }
        }

        return false;
    }

    public void clear() {
        this.list.clear();
    }

    public void size() {
        this.list.size();
    }

    @Override
    public Iterator iterator() {
        return this.list.iterator();
    }
}
