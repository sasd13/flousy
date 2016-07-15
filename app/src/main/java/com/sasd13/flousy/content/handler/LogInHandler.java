package com.sasd13.flousy.content.handler;

import com.sasd13.flousy.LogInActivity;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.dao.db.SQLitePasswordDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.javaex.db.DAOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class LogInHandler {

    private LogInActivity logInActivity;
    private SQLiteDAO dao;
    private Map<String, String[]> parameters;

    public LogInHandler(LogInActivity logInActivity) {
        this.logInActivity = logInActivity;
        dao = SQLiteDAO.create(logInActivity);
        parameters = new HashMap<>();
    }

    public void logIn(String email, String password) {
        parameters.clear();
        parameters.put(Parameter.EMAIL.getName(), new String[]{ email });

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
            logInActivity.onError(LogInActivity.self.getResources().getString(com.sasd13.androidex.R.string.message_error_login));
        } else {
            logInActivity.onSuccess(customer);
        }
    }

    private boolean passwordMatches(SQLiteDAO dao, Customer customer, String password) {
        return new SQLitePasswordDAO(dao.getDB()).contains(password, customer.getId());
    }
}
