package flousy.db.dao;

import flousy.bean.Customer;

public interface CustomerDAO {

    String CUSTOMER_TABLE_NAME = "customers";

    String CUSTOMER_ID = "customer_id";
    String CUSTOMER_FIRSTNAME = "customer_firstname";
    String CUSTOMER_LASTNAME = "customer_lastname";
    String CUSTOMER_EMAIL = "customer_email";
    String CUSTOMER_PASSWORD = "customer_password";

    void insert(Customer customer);

    void update(Customer customer);

    void delete(long id);

    Customer select(long id);

    Customer selectByEmail(String email);

    boolean containsByEmail(String email);
}