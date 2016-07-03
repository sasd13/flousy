package com.sasd13.flousy.content.handler;

import android.content.Context;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.content.form.SignForm;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.dao.db.SQLitePasswordDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.LayeredPersistor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class SignHandler {

    private static SignForm signForm;
    private static SQLiteDAO dao;
    private static LayeredPersistor persistor;

    public static void init(Context context) {
        signForm = new SignForm(context);
        dao = SQLiteDAO.getInstance();
        persistor = new LayeredPersistor(dao);
    }

    public static SignForm getSignForm() {
        return signForm;
    }

    public static String[] validFormInputs() {
        //TODO

        return new String[]{};
    }

    public static Customer createCustomer() {
        String email = signForm.getEmail();

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.EMAIL.getName(), new String[]{ email });

        if (persistor.read(parameters, Customer.class).isEmpty()) {
            Customer customer = new Customer();
            editCustomerWithForm(customer);
            performSign(customer);

            return customer;
        } else {
            return null;
        }
    }

    private static void editCustomerWithForm(Customer customer) {
        customer.setFirstName(signForm.getFirstName());
        customer.setLastName(signForm.getLastName());
        customer.setEmail(signForm.getEmail());
    }

    private static void performSign(Customer customer) {
        try {
            dao.open();
            dao.beginTransaction();
            dao.getEntityDAO(Customer.class).insert(customer);

            String password = signForm.getPassword();
            SQLitePasswordDAO passwordDAO = new SQLitePasswordDAO(dao.getDB());
            passwordDAO.insert(password, customer.getId());

            dao.getEntityDAO(Account.class).insert(customer.getAccount());
            dao.commit();
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            dao.endTransaction();
            dao.close();
        }
    }
}
