package flousy.bean.customer;

import flousy.util.List;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListCustomers extends List<Customer> {

    @Override
    public Customer get(Object id) {
        for (Customer customer : this.list) {
            if (customer.getId() == (long) id) {
                return customer;
            }
        }

        return null;
    }
}
