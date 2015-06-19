package flousy.content.finance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListIncomes implements Iterable {

    private List<Income> list;

    public ListIncomes() {
        this.list = new ArrayList<>();
    }

    public boolean add(Income income) {
        return this.list.add(income);
    }

    public boolean remove(Income income) {
        return this.list.remove(income);
    }

    public Income remove(int index) {
        return this.list.remove(index);
    }

    public Income get(int index) {
        return this.list.get(index);
    }

    public boolean contains(Income income) {
        for (Income income2 : this.list) {
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
