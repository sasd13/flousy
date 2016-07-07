package com.sasd13.flousy.content.handler;

import com.sasd13.flousy.R;
import com.sasd13.flousy.SignActivity;
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

    public static void sign(SignActivity signActivity, SignForm signForm) {
        String[] errors = validFormInputs(signForm);

        if (errors.length != 0) {
            signActivity.onError(errors[0]);
        } else {
            SQLiteDAO dao = SQLiteDAO.create(signActivity);
            LayeredPersistor persistor = new LayeredPersistor(dao);

            Map<String, String[]> parameters = new HashMap<>();
            parameters.put(Parameter.EMAIL.getName(), new String[]{ signForm.getEmail() });

            if (!persistor.read(parameters, Customer.class).isEmpty()) {
                signActivity.onError(signActivity.getResources().getString(R.string.message_email_exists));
            } else {
                Customer customer = new Customer();

                signActivity.editCustomerWithForm(customer);
                performSign(dao, customer, signForm.getPassword());
                signActivity.onSuccess(customer);
            }
        }
    }

    public static String[] validFormInputs(SignForm signForm) {
        //TODO

        return new String[]{};
    }

    private static void performSign(SQLiteDAO dao, Customer customer, String password) {
        try {
            dao.open();
            dao.beginTransaction();
            dao.getEntityDAO(Customer.class).insert(customer);

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
