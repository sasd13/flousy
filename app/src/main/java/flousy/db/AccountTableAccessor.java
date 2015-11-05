package flousy.db;

import flousy.bean.customer.Customer;
import flousy.bean.trading.ITradingAccount;

/**
 * Created by Samir on 11/06/2015.
 */
public interface AccountTableAccessor extends TableAccessor<ITradingAccount> {

    String ACCOUNT_TABLE_NAME = "accounts";

    String ACCOUNT_ID = "account_id";
    String ACCOUNT_DATEOPENING = "account_dateopening";
    String ACCOUNT_TYPE = "account_type";
    String ACCOUNT_SOLD = "account_sold";
    String CUSTOMERS_CUSTOMER_ID = "customers_customer_id";

    long insert(ITradingAccount tradingAccount, Customer customer);

    ITradingAccount selectAccountByCustomer(long customerId);
}
