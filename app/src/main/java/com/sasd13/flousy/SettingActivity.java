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
import com.sasd13.flousy.gui.widget.recycler.form.FormItemText;
import com.sasd13.flousy.gui.widget.recycler.form.input.FormInput;
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
        FormItemText formItemText;

        for (int i = 0; i<3; i++) {
            formItemText = new FormItemText();

            switch (i) {
                case 0:
                    formItemText.setId(FormCustomerViewHolder.FORMIDENTITY_ID_FIRSTNAME);
                    formItemText.setLabel("Prénom");
                    break;
                case 1:
                    formItemText.setId(FormCustomerViewHolder.FORMIDENTITY_ID_LASTNAME);
                    formItemText.setLabel("Nom");
                    break;
                case 2:
                    formItemText.setId(FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL);
                    formItemText.setLabel("Email");
                    break;
            }

            formItemText.setInput(new FormInput() {
                @Override
                public String getStringValue() {
                    return String.valueOf(getValue());
                }
            });
            formItemText.setAction(this);

            formCustomer.formIdentity.addItem(formItemText);
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
                    ((FormItemText) formItem).getInput().setValue(customer.getFirstName());
                    break;
                case FormCustomerViewHolder.FORMIDENTITY_ID_LASTNAME:
                    ((FormItemText) formItem).getInput().setValue(customer.getLastName());
                    break;
                case FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL:
                    ((FormItemText) formItem).getInput().setValue(customer.getEmail());
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
        String email = (String) ((FormItemText) formCustomer.formIdentity
                .findItemById(FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL))
                .getInput().getValue();

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
                    customer.setFirstName((String) ((FormItemText) formItem).getInput().getValue());
                    break;
                case FormCustomerViewHolder.FORMIDENTITY_ID_LASTNAME:
                    customer.setLastName((String) ((FormItemText) formItem).getInput().getValue());
                    break;
                case FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL:
                    customer.setEmail((String) ((FormItemText) formItem).getInput().getValue());
                    break;
            }
        }
    }

    @Override
    public void execute(final FormItem formItem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(formItem.getLabel());

        final EditText editText = new EditText(this);
        if (((FormItemText) formItem).getInput() != null) {
            editText.setText(((FormItemText) formItem).getInput().getStringValue());
        } else {
            editText.setHint(((FormItemText) formItem).getInput().getHint());
        }

        switch (formItem.getId()) {
            case FormCustomerViewHolder.FORMIDENTITY_ID_FIRSTNAME:
            case FormCustomerViewHolder.FORMIDENTITY_ID_LASTNAME:
            case FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL:
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((FormItemText) formItem).getInput().setValue(editText.getText().toString());
                    }
                });

                break;
            case FormCustomerViewHolder.FORMIDENTITY_ID_PASSWORD:
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((FormItemText) formItem).getInput().setValue(editText.getText().toString());
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