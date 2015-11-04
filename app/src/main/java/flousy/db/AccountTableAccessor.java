package flousy.db;

import flousy.content.customer.Account;
import flousy.content.customer.Customer;

/**
 * Created by Samir on 11/06/2015.
 */
public interface AccountTableAccessor extends TableAccessor<Account> {

    String ACCOUNT_TABLE_NAME = "accounts";

    String ACCOUNT_ID = "account_id";
    String CUSTOMERS_CUSTOMER_ID = "customers_customer_id";

    long insert(Account account, Customer customer);
}
