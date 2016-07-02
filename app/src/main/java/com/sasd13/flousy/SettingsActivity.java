package com.sasd13.flousy;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.form.Form;
import com.sasd13.androidex.gui.widget.recycler.form.FormFactory;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.form.SettingFormHandler;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.LayeredPersistor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsActivity extends MotherActivity {

    private SettingFormHandler settingFormHandler;
    private Customer customer;
    private LayeredPersistor persistor = new LayeredPersistor(SQLiteDAO.getInstance());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        GUIHelper.colorTitles(this);
        createFormSetting();
    }

    private void createFormSetting() {
        settingFormHandler = new SettingFormHandler(this);
        FormFactory formFactory = new FormFactory(this);
        Form form = (Form) formFactory.makeBuilder().build((RecyclerView) findViewById(R.id.settings_recyclerview));

        RecyclerHelper.fill(form, settingFormHandler.fabricate(), formFactory);
    }

    @Override
    protected void onStart() {
        super.onStart();

        customer = persistor.read(SessionHelper.getExtraIdFromSession(Extra.CUSTOMER_ID), Customer.class);
        setCustomerSettings();
    }

    private void setCustomerSettings() {
        settingFormHandler.setFirstName(customer.getFirstName());
        settingFormHandler.setLastName(customer.getLastName());
        settingFormHandler.setEmail(customer.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sign, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sign_action_done:
                updateCustomer();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateCustomer() {
        String[] tabFormErrors = validFormCustomer();

        if (true) {
            tryToPerformUpdateCustomer();
        } else {
            OptionDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
        }
    }

    private String[] validFormCustomer() {
        //TODO

        return null;
    }

    private void tryToPerformUpdateCustomer() {
        String email = settingFormHandler.getEmail();

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.EMAIL.getName(), new String[]{ email });

        List<Customer> customers = persistor.read(parameters, Customer.class);
        if (customers.isEmpty() || customers.get(0).getId() == SessionHelper.getExtraIdFromSession(Extra.CUSTOMER_ID)) {
            performUpdate();
        } else {
            OptionDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.title_error),
                    getResources().getString(R.string.message_email_exists) + " " + email);
        }
    }

    private void performUpdate() {
        editCustomerWithForm();
        persistor.update(customer);
        Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
    }

    private void editCustomerWithForm() {
        customer.setFirstName(settingFormHandler.getFirstName());
        customer.setLastName(settingFormHandler.getLastName());
        customer.setEmail(settingFormHandler.getEmail());
    }
}