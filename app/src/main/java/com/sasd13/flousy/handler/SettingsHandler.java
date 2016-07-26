package com.sasd13.flousy.handler;

import com.sasd13.flousy.R;
import com.sasd13.flousy.SettingsActivity;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.form.FormException;
import com.sasd13.flousy.form.SettingsForm;
import com.sasd13.flousy.util.Binder;
import com.sasd13.flousy.util.EnumParameter;
import com.sasd13.javaex.db.LayeredPersistor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class SettingsHandler {

    private SettingsActivity settingsActivity;
    private LayeredPersistor persistor;
    private Map<String, String[]> parameters;

    public SettingsHandler(SettingsActivity settingsActivity) {
        this.settingsActivity = settingsActivity;
        persistor = new LayeredPersistor(SQLiteDAO.create(settingsActivity));
        parameters = new HashMap<>();
    }

    public Customer readCustomer(long id) {
        return persistor.read(id, Customer.class);
    }

    public void updateCustomer(Customer customer, SettingsForm settingsForm) {
        parameters.clear();
        try {
            parameters.put(EnumParameter.EMAIL.getName(), new String[]{ settingsForm.getEditable().getEmail() });

            List<Customer> customers = persistor.read(parameters, Customer.class);
            if (!customers.isEmpty() && customers.get(0).getId() != customer.getId()) {
                String error = settingsActivity.getResources().getString(R.string.message_email_exists);

                settingsActivity.onError(error);
            } else {
                Binder.bind(customer, settingsForm.getEditable());
                persistor.update(customer);

                settingsActivity.onUpdateSucceeded();
            }
        } catch (FormException e) {
            settingsActivity.onError(e.getMessage());
        }
    }
}
