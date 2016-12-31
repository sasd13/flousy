package com.sasd13.flousy.service;

import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.db.SQLiteDAO;
import com.sasd13.flousy.db.dao.impl.SQLitePasswordDAO;
import com.sasd13.flousy.util.EnumParameter;
import com.sasd13.javaex.dao.DAOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 31/12/2016.
 */

public class LogInService {

    private SQLiteDAO dao;
    private Map<String, String[]> parameters;

    public LogInService() {
        parameters = new HashMap<>();
    }

    public boolean logIn(String email, String password) {
        parameters.clear();
        parameters.put(EnumParameter.EMAIL.getName(), new String[]{ email });

        Customer customer = null;

        try {
            dao.open();

            List<Customer> customers = dao.getEntityDAO(Customer.class).select(parameters);
            if (customers.size() == 1 && passwordMatches(dao, customers.get(0), password)) {
                customer = customers.get(0);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }

        if (customer == null) {
            logInActivity.onError(com.sasd13.androidex.R.string.message_error_login);
        } else {
            logInActivity.onLogInSucceeded(customer);
        }
    }

    private boolean passwordMatches(Customer customer, String password) {
        return new SQLitePasswordDAO(dao.getDB()).contains(password, customer.getId());
    }
}
