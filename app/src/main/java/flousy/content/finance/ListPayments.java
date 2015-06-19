package flousy.content.finance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListPayments implements Iterable {
    
    private List<Payment> list;
    
    public ListPayments() {
        this.list = new ArrayList<>();
    }

    public boolean add(Payment payment) {
        return this.list.add(payment);
    }

    public boolean remove(Payment payment) {
        return this.list.remove(payment);
    }

    public Payment remove(int index) {
        return this.list.remove(index);
    }

    public Payment get(int index) {
        return this.list.get(index);
    }

    public boolean contains(Payment payment) {
        for (Payment payment2 : this.list) {
            if (payment2.equals(payment)) {
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
