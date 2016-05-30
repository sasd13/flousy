package com.sasd13.flousy;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.gui.widget.recycler.form.Form;
import com.sasd13.flousy.gui.widget.recycler.form.FormItem;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.LayeredPersistor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingActivity extends MotherActivity implements FormItem.Action {

    private class FormCustomerViewHolder {
        public static final int FORMIDENTITY_ID_FIRSTNAME = 0;
        public static final int FORMIDENTITY_ID_LASTNAME = 1;
        public static final int FORMIDENTITY_ID_EMAIL = 2;
        public static final int FORMIDENTITY_ID_PASSWORD = 3;

        public Form formIdentity;
    }

    private FormCustomerViewHolder formCustomer;

    private Customer customer;
    private LayeredPersistor persistor = new LayeredPersistor(SQLiteDAO.getInstance());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        createFormCustomer();
    }

    private void createFormCustomer() {
        formCustomer = new FormCustomerViewHolder();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.setting_form_user_recyclerview_identity);
        formCustomer.formIdentity = new Form(recyclerView, R.layout.formitem);
        formCustomer.formIdentity.setScrollingDisabled(true);

        addFormIdentityItems();
    }

    private void addFormIdentityItems() {
        FormItem formItem;

        for (int i = 0; i<3; i++) {
            formItem = new FormItem();

            switch (i) {
                case 0:
                    formItem.setId(FormCustomerViewHolder.FORMIDENTITY_ID_FIRSTNAME);
                    formItem.setName("Prénom");
                    break;
                case 1:
                    formItem.setId(FormCustomerViewHolder.FORMIDENTITY_ID_LASTNAME);
                    formItem.setName("Nom");
                    break;
                case 2:
                    formItem.setId(FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL);
                    formItem.setName("Email");
                    break;
            }

            formItem.setAction(this);

            formCustomer.formIdentity.addItem(formItem);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        customer = persistor.read(SessionHelper.getExtraIdFromSession(Extra.CUSTOMER_ID), Customer.class);

        fillFormCustomer();
    }

    private void fillFormCustomer() {
        for (FormItem formItem : formCustomer.formIdentity.getItems(FormItem.class)) {
            switch (formItem.getId()) {
                case FormCustomerViewHolder.FORMIDENTITY_ID_FIRSTNAME:
                    formItem.setValue(customer.getFirstName());
                    break;
                case FormCustomerViewHolder.FORMIDENTITY_ID_LASTNAME:
                    formItem.setValue(customer.getLastName());
                    break;
                case FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL:
                    formItem.setValue(customer.getEmail());
                    break;
            }
        }
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
            case R.id.menu_sign_action_accept:
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
            CustomDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
        }
    }

    private String[] validFormCustomer() {
        //TODO

        return null;
    }

    private void tryToPerformUpdateCustomer() {
        String email = formCustomer.formIdentity.findItemById(FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL).getValue();

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.EMAIL.getName(), new String[]{ email });

        List<Customer> customers = persistor.read(parameters, Customer.class);
        if (customers.isEmpty() || customers.get(0).getId() == SessionHelper.getExtraIdFromSession(Extra.CUSTOMER_ID)) {
            performUpdate();
        } else {
            CustomDialog.showOkDialog(this, "Error update", "Email (" + email + ") already exists");
        }
    }

    private void performUpdate() {
        editCustomerWithForm(customer);
        persistor.update(customer);
        Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
    }

    private void editCustomerWithForm(Customer customer) {
        for (FormItem formItem : formCustomer.formIdentity.getItems(FormItem.class)) {
            switch (formItem.getId()) {
                case FormCustomerViewHolder.FORMIDENTITY_ID_FIRSTNAME:
                    customer.setFirstName(formItem.getValue());
                    break;
                case FormCustomerViewHolder.FORMIDENTITY_ID_LASTNAME:
                    customer.setLastName(formItem.getValue());
                    break;
                case FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL:
                    customer.setEmail(formItem.getValue());
                    break;
            }
        }
    }

    @Override
    public void execute(final FormItem formItem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(formItem.getName());

        final EditText editText = new EditText(this);
        if (formItem.getValue() != null) {
            editText.setText(formItem.getValue());
        } else {
            editText.setHint(formItem.getValue());
        }

        switch (formItem.getId()) {
            case FormCustomerViewHolder.FORMIDENTITY_ID_FIRSTNAME:
            case FormCustomerViewHolder.FORMIDENTITY_ID_LASTNAME:
            case FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL:
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        formItem.setValue(editText.getText().toString());
                    }
                });

                break;
            case FormCustomerViewHolder.FORMIDENTITY_ID_PASSWORD:
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        formItem.setValue(editText.getText().toString());
                    }
                });

                break;
        }

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setView(editText);

        builder.show();
    }
}