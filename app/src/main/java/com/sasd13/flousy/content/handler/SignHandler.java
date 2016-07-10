package com.sasd13.flousy.content.handler;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.flousy.R;
import com.sasd13.flousy.SignActivity;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.content.form.SignForm;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.dao.db.SQLitePasswordDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.LayeredPersistor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class SignHandler {

    private SignActivity signActivity;
    private SignForm signForm;
    private SQLiteDAO dao;
    private LayeredPersistor persistor;
    private Map<String, String[]> parameters;

    public SignHandler(SignActivity signActivity, SignForm signForm) {
        this.signActivity = signActivity;
        this.signForm = signForm;
        dao = SQLiteDAO.create(signActivity);
        persistor = new LayeredPersistor(dao);
        parameters = new HashMap<>();
    }

    public void sign() {
        String[] errors = validFormInputs();

        if (errors.length != 0) {
            onError(errors[0]);
        } else {
            parameters.clear();
            parameters.put(Parameter.EMAIL.getName(), new String[]{ signForm.getEmail() });

            if (!persistor.read(parameters, Customer.class).isEmpty()) {
                onError(signActivity.getResources().getString(R.string.message_email_exists));
            } else {
                Customer customer = new Customer();

                editCustomerWithForm(customer);
                performSign(customer);
                onSuccess(customer);
            }
        }
    }

    private String[] validFormInputs() {
        //TODO

        return new String[]{};
    }

    private void onError(String error) {
        OptionDialog.showOkDialog(signActivity, signActivity.getResources().getString(R.string.title_error), error);
    }

    private void editCustomerWithForm(Customer customer) {
        customer.setFirstName(signForm.getFirstName());
        customer.setLastName(signForm.getLastName());
        customer.setEmail(signForm.getEmail());
    }

    private void performSign(Customer customer) {
        try {
            dao.open();
            dao.beginTransaction();
            dao.getEntityDAO(Customer.class).insert(customer);

            SQLitePasswordDAO passwordDAO = new SQLitePasswordDAO(dao.getDB());
            passwordDAO.insert(signForm.getPassword(), customer.getId());

            dao.getEntityDAO(Account.class).insert(customer.getAccount());
            dao.commit();
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            dao.endTransaction();
            dao.close();
        }
    }

    private void onSuccess(Customer customer) {
        SessionHelper.logIn(signActivity, customer);
    }
}
