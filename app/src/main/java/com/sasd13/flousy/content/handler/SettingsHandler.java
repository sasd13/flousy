package com.sasd13.flousy.content.handler;

import android.content.Context;

import com.sasd13.flousy.R;
import com.sasd13.flousy.SettingsActivity;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.content.form.SettingsForm;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.javaex.db.LayeredPersistor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class SettingsHandler {

    public static Customer readCustomer(Context context, long id) {
        LayeredPersistor persistor = new LayeredPersistor(SQLiteDAO.create(context));

        return persistor.read(id, Customer.class);
    }

    public static void updateCustomer(SettingsActivity settingsActivity, Customer customer, SettingsForm settingsForm) {
        String[] errors = validFormInputs(settingsForm);

        if (errors.length != 0) {
            settingsActivity.onError(errors[0]);
        } else {
            LayeredPersistor persistor = new LayeredPersistor(SQLiteDAO.create(settingsActivity));

            Map<String, String[]> parameters = new HashMap<>();
            parameters.put(Parameter.EMAIL.getName(), new String[]{ settingsForm.getEmail() });

            List<Customer> customers = persistor.read(parameters, Customer.class);
            if (!customers.isEmpty() && customers.get(0).getId() != customer.getId()) {
                String error = settingsActivity.getResources().getString(R.string.message_email_exists);

                settingsActivity.onError(error);
            } else {
                settingsActivity.editCustomerWithForm(customer);
                persistor.update(customer);

                settingsActivity.onSuccess();
            }
        }
    }

    public static String[] validFormInputs(SettingsForm settingsForm) {
        //TODO

        return new String[]{};
    }
}