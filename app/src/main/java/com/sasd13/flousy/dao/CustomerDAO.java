package com.sasd13.flousy.dao;

import com.sasd13.flousy.bean.Customer;
import com.sasd13.javaex.db.IEntityDAO;

public interface CustomerDAO extends IEntityDAO<Customer> {

    String TABLE = "customers";

    String COLUMN_ID = "id";
    String COLUMN_FIRSTNAME = "firstname";
    String COLUMN_LASTNAME = "lastname";
    String COLUMN_EMAIL = "email";
}