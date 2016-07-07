package com.sasd13.flousy;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactoryProducer;
import com.sasd13.androidex.gui.widget.recycler.RecyclerType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.form.SettingsForm;
import com.sasd13.flousy.content.handler.SettingsHandler;
import com.sasd13.flousy.util.SessionHelper;

public class SettingsActivity extends MotherActivity {

    private SettingsForm settingsForm;
    private Customer customer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        GUIHelper.colorTitles(this);
        buildFormSettings();
    }

    private void buildFormSettings() {
        settingsForm = new SettingsForm(this);
        RecyclerFactory formFactory = RecyclerFactoryProducer.produce(RecyclerType.FORM, this);
        Recycler form = formFactory.makeBuilder().build((RecyclerView) findViewById(R.id.settings_recyclerview));

        RecyclerHelper.addAll(form, settingsForm.fabricate(), formFactory);
        fillCustomerSettings();
    }

    private void fillCustomerSettings() {
        customer = SettingsHandler.readCustomer(this, SessionHelper.getExtraIdFromSession(this, Extra.CUSTOMER_ID));

        settingsForm.setFirstName(customer.getFirstName());
        settingsForm.setLastName(customer.getLastName());
        settingsForm.setEmail(customer.getEmail());
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
        SettingsHandler.updateCustomer(this, customer, settingsForm);
    }

    public void editCustomerWithForm(Customer customer) {
        customer.setFirstName(settingsForm.getFirstName());
        customer.setLastName(settingsForm.getLastName());
        customer.setEmail(settingsForm.getEmail());
    }

    public void onSuccess() {
        Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
    }

    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}