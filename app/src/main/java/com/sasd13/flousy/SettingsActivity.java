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
import com.sasd13.flousy.content.handler.SettingsHandler;
import com.sasd13.flousy.util.SessionHelper;

public class SettingsActivity extends MotherActivity {

    private Customer customer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SettingsHandler.init(this);
        setContentView(R.layout.activity_settings);
        GUIHelper.colorTitles(this);
        buildFormSettings();
    }

    private void buildFormSettings() {
        FormFactory formFactory = new FormFactory(this);
        Form form = (Form) formFactory.makeBuilder().build((RecyclerView) findViewById(R.id.settings_recyclerview));

        RecyclerHelper.fill(form, SettingsHandler.getSettingForm().fabricate(), formFactory);
        fillCustomerSettings();
    }

    private void fillCustomerSettings() {
        customer = SettingsHandler.readCustomer(SessionHelper.getExtraIdFromSession(Extra.CUSTOMER_ID));

        SettingsHandler.fillSettingsCustomer(customer);
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
        String[] errors = SettingsHandler.validFormInputs();

        if (errors.length == 0) {
            errors = SettingsHandler.updateCustomer(customer);

            if (errors.length == 0) {
                Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
            } else {
                OptionDialog.showOkDialog(this, getResources().getString(R.string.title_error), errors[0]);
            }
        } else {
            OptionDialog.showOkDialog(this, getResources().getString(R.string.title_error), errors[0]);
        }
    }
}