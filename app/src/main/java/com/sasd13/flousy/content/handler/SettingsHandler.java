package com.sasd13.flousy.content.handler;

import android.content.Context;

import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.content.form.SettingForm;
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

    private static Context context;
    private static SettingForm settingForm;
    private static LayeredPersistor persistor;

    public static void init(Context context) {
        SettingsHandler.context = context;
        settingForm = new SettingForm(context);
        persistor = new LayeredPersistor(SQLiteDAO.getInstance());
    }

    public static SettingForm getSettingForm() {
        return settingForm;
    }

    public static Customer readCustomer(long id) {
        return persistor.read(id, Customer.class);
    }

    public static void fillSettingsCustomer(Customer customer) {
        settingForm.setFirstName(customer.getFirstName());
        settingForm.setLastName(customer.getLastName());
        settingForm.setEmail(customer.getEmail());
    }

    public static String[] validFormInputs() {
        //TODO

        return new String[]{};
    }

    public static String[] updateCustomer(Customer customer) {
        String email = settingForm.getEmail();

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.EMAIL.getName(), new String[]{ email });

        List<Customer> customers = persistor.read(parameters, Customer.class);
        if (customers.isEmpty() || customers.get(0).getId() == customer.getId()) {
            editCustomerWithForm(customer);
            persistor.update(customer);

            return new String[]{};
        } else {
            return new String[]{context.getResources().getString(R.string.message_email_exists)};
        }
    }

    private static void editCustomerWithForm(Customer customer) {
        customer.setFirstName(settingForm.getFirstName());
        customer.setLastName(settingForm.getLastName());
        customer.setEmail(settingForm.getEmail());
    }
}
