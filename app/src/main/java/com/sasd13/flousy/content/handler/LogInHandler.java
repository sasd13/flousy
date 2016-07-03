package com.sasd13.flousy.content.handler;

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

    private static SQLiteDAO dao;

    public static void init() {
        dao = SQLiteDAO.getInstance();
    }

    public static Customer logIn(String email, String password) {
        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.EMAIL.getName(), new String[]{email});

        try {
            dao.open();

            List<Customer> customers = dao.getEntityDAO(Customer.class).select(parameters);
            if (customers.size() == 1 && passwordMatches(customers.get(0), password)) {
                return customers.get(0);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }

        return null;
    }

    private static boolean passwordMatches(Customer customer, String password) {
        return new SQLitePasswordDAO(dao.getDB()).contains(password, customer.getId());
    }
}
