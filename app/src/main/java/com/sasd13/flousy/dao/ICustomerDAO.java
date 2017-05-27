package com.sasd13.flousy.dao;

import com.sasd13.flousy.bean.Customer;

public interface ICustomerDAO {

    String TABLE = "customers";
    String COLUMN_CODE = "_code";
    String COLUMN_FIRSTNAME = "_firstname";
    String COLUMN_LASTNAME = "_lastname";
    String COLUMN_EMAIL = "_email";

    long create(Customer customer);

    void update(Customer customer);

    Customer read(String intermediary);
}