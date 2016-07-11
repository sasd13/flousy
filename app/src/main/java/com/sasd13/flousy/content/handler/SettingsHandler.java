package com.sasd13.flousy.content.handler;

import android.widget.Toast;

import com.sasd13.flousy.R;
import com.sasd13.flousy.SettingsActivity;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.form.SettingsForm;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.LayeredPersistor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class SettingsHandler {

    private SettingsActivity settingsActivity;
    private SettingsForm settingsForm;
    private LayeredPersistor persistor;
    private Map<String, String[]> parameters;
    private Customer customer;

    public SettingsHandler(SettingsActivity settingsActivity, SettingsForm settingsForm) {
        this.settingsActivity = settingsActivity;
        this.settingsForm = settingsForm;
        persistor = new LayeredPersistor(SQLiteDAO.create(settingsActivity));
        parameters = new HashMap<>();
    }

    public Customer readCustomer() {
        long id = SessionHelper.getExtraIdFromSession(settingsActivity, Extra.CUSTOMER_ID);
        customer = persistor.read(id, Customer.class);

        return customer;
    }

    public void updateCustomer() {
        parameters.clear();
        parameters.put(Parameter.EMAIL.getName(), new String[]{ settingsForm.getEditable().getEmail() });

        List<Customer> customers = persistor.read(parameters, Customer.class);
        if (!customers.isEmpty() && customers.get(0).getId() != customer.getId()) {
            String error = settingsActivity.getResources().getString(R.string.message_email_exists);

            onError(error);
        } else {
            editCustomerWithForm(customer);
            persistor.update(customer);

            onSuccess();
        }
    }

    private void onError(String error) {
        Toast.makeText(settingsActivity, error, Toast.LENGTH_SHORT).show();
    }

    private void editCustomerWithForm(Customer customer) {
        Customer customerFromForm = settingsForm.getEditable();

        customer.setFirstName(customerFromForm.getFirstName());
        customer.setLastName(customerFromForm.getLastName());
        customer.setEmail(customerFromForm.getEmail());
    }

    private void onSuccess() {
        Toast.makeText(settingsActivity, R.string.message_saved, Toast.LENGTH_SHORT).show();
    }
}
