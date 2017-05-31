package com.sasd13.flousy.view.fragment.setting;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.util.Extra;
import com.sasd13.flousy.view.gui.form.SettingsForm;

public class SettingFragment extends Fragment {

    private SettingsHandler settingsHandler;
    private SettingsForm formSettings;
    private Customer customer;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_setting);
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
        customer = settingsHandler.readCustomer(SessionHelper.getExtraId(this, Extra.ID_CUSTOMER_EMAIL));

        formSettings.bindCustomer(customer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_signup, menu);

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
        Snackbar.make(getContentView(), R.string.message_saved, Snackbar.LENGTH_SHORT).show();
    }

    public void onError(@StringRes int error) {
        Snackbar.make(getContentView(), error, Snackbar.LENGTH_SHORT).show();
    }
}