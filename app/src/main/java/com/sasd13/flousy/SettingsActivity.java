package com.sasd13.flousy;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.form.SettingsForm;
import com.sasd13.flousy.handler.SettingsHandler;
import com.sasd13.flousy.util.SessionHelper;

public class SettingsActivity extends MotherActivity {

    private SettingsHandler settingsHandler;
    private SettingsForm formSettings;
    private Customer customer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        GUIHelper.colorTitles(this);

        settingsHandler = new SettingsHandler(this);
        formSettings = new SettingsForm(this);

        buildView();
    }

    private void buildView() {
        Recycler form = RecyclerFactory
                .makeBuilder(EnumFormType.FORM)
                .build((RecyclerView) findViewById(R.id.settings_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, formSettings.getHolder());
        fillFormSettings();
    }

    private void fillFormSettings() {
        customer = settingsHandler.readCustomer(SessionHelper.getExtraId(this, Extra.CUSTOMER_ID));

        formSettings.bindCustomer(customer);
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
        settingsHandler.updateCustomer(customer, formSettings);
    }

    public void onUpdateSucceeded() {
        Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
    }

    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}