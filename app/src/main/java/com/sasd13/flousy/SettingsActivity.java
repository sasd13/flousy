package com.sasd13.flousy;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactoryType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.content.form.SettingsForm;
import com.sasd13.flousy.content.handler.SettingsHandler;

public class SettingsActivity extends MotherActivity {

    private SettingsForm settingsForm;
    private SettingsHandler settingsHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settingsForm = new SettingsForm(this);
        settingsHandler = new SettingsHandler(this, settingsForm);

        setContentView(R.layout.activity_settings);
        GUIHelper.colorTitles(this);
        buildFormSettings();
    }

    private void buildFormSettings() {
        Recycler form = RecyclerHelper.produce(RecyclerFactoryType.FORM, (RecyclerView) findViewById(R.id.settings_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, settingsForm.fabricate());
        fillCustomerSettings();
    }

    private void fillCustomerSettings() {
        Customer customer = settingsHandler.readCustomer();

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
        settingsHandler.updateCustomer();
    }
}