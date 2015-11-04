package flousy.content.customer;

import flousy.util.FlousyList;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListCustomers extends FlousyList<Customer> {

    @Override
    public Customer get(long id) {
        for (Customer customer : this.list) {
            if (customer.getId() == id) {
                return customer;
            }
        }

        return null;
    }
}
