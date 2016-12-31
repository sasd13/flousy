package com.sasd13.flousy.handler;

import com.sasd13.flousy.R;
import com.sasd13.flousy.activities.SignActivity;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.gui.form.SignForm;
import com.sasd13.flousy.db.SQLiteDAO;
import com.sasd13.flousy.db.dao.impl.SQLitePasswordDAO;
import com.sasd13.flousy.util.Binder;
import com.sasd13.flousy.util.EnumParameter;
import com.sasd13.javaex.dao.DAOException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class SignHandler {

    private SignActivity signActivity;
    private SQLiteDAO dao;
    private LayeredPersistor persistor;
    private Map<String, String[]> parameters;

    public SignHandler(SignActivity signActivity) {
        this.signActivity = signActivity;
        dao = SQLiteDAO.create(signActivity);
        persistor = new LayeredPersistor(dao);
        parameters = new HashMap<>();
    }

    public void sign(SignForm signForm) {
        parameters.clear();
        try {
            parameters.put(EnumParameter.EMAIL.getName(), new String[]{ signForm.getEditable().getEmail() });

            if (!persistor.read(parameters, Customer.class).isEmpty()) {
                signActivity.onError(R.string.message_email_exists);
            } else {
                Customer customer = new Customer();

                Binder.bind(customer, signForm.getEditable());
                performSign(customer, signForm.getPassword());
                signActivity.onSignSucceeded(customer);
            }
        } catch (FormException e) {
            signActivity.onError(e.getResMessage());
        }
    }

    private void performSign(Customer customer, String password) {
        dao.open();

        try {
            dao.beginTransaction();
            dao.getEntityDAO(Customer.class).insert(customer);

            SQLitePasswordDAO passwordDAO = new SQLitePasswordDAO(dao.getDB());
            passwordDAO.insert(password, customer.getId());

            dao.getEntityDAO(Account.class).insert(customer.getAccount());
            dao.commit();
        } catch (DAOException e) {
            dao.rollback();
            e.printStackTrace();
        } finally {
            dao.endTransaction();
            dao.close();
        }
    }
}
