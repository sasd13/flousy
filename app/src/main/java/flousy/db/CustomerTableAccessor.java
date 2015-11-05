package flousy.db;

import flousy.bean.customer.Customer;

/**
 * Created by Samir on 11/06/2015.
 */
public interface CustomerTableAccessor extends TableAccessor<Customer> {

    String CUSTOMER_TABLE_NAME = "customers";

    String CUSTOMER_ID = "customer_id";
    String CUSTOMER_FIRSTNAME = "customer_firstname";
    String CUSTOMER_LASTNAME = "customer_lastname";
    String CUSTOMER_EMAIL = "customer_email";
    String CUSTOMER_PASSWORD = "customer_password";
    String CUSTOMER_PHONE = "customer_phone";

    Customer selectByEmail(String email);
}
