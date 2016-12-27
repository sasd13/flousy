package com.sasd13.flousy.dao;

import com.sasd13.flousy.bean.Customer;
import com.sasd13.javaex.dao.ISession;

public interface CustomerDAO extends ISession<Customer> {

    String TABLE = "customers";
    String COLUMN_ID = "_id";
    String COLUMN_FIRSTNAME = "_firstname";
    String COLUMN_LASTNAME = "_lastname";
    String COLUMN_EMAIL = "_email";
}