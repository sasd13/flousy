package flousy.content.customer;

import flousy.util.FlousyList;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListAccounts extends FlousyList<Account> {

    @Override
    public Account get(long id) {
        for (Account customer : this.list) {
            if (customer.getId() == id) {
                return customer;
            }
        }

        return null;
    }
}
