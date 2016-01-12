package com.sasd13.flousy.db;

import com.sasd13.flousy.bean.Customer;
import com.sasd13.javaex.db.IEntityDAO;

public interface CustomerDAO extends IEntityDAO<Customer> {

    String TABLE = "customers";

    String COLUMN_ID = "customer_id";
    String COLUMN_NUMBER = "customer_number";
    String COLUMN_FIRSTNAME = "customer_firstname";
    String COLUMN_LASTNAME = "customer_lastname";
    String COLUMN_EMAIL = "customer_email";
    String COLUMN_PASSWORD = "customer_password";

    Customer selectByNumber(String number);

    Customer selectByEmail(String email);
}