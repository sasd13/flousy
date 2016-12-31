package com.sasd13.flousy.handler;

import com.sasd13.flousy.R;
import com.sasd13.flousy.activities.SettingsActivity;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.db.SQLiteDAO;
import com.sasd13.flousy.gui.form.SettingsForm;
import com.sasd13.flousy.util.Binder;
import com.sasd13.flousy.util.EnumParameter;

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
                settingsActivity.onError(R.string.message_email_exists);
            } else {
                Binder.bind(customer, settingsForm.getEditable());
                persistor.update(customer);

                settingsActivity.onUpdateSucceeded();
            }
        } catch (FormException e) {
            settingsActivity.onError(e.getResMessage());
        }
    }
}
