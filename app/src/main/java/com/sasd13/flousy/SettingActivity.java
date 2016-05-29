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
import com.sasd13.flousy.gui.widget.recycler.form.FormItemAction;
import com.sasd13.flousy.gui.widget.recycler.form.FormItemInput;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.LayeredPersistor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingActivity extends MotherActivity implements FormItemAction {

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

        addFormIdentityItems();
    }

    private void addFormIdentityItems() {
        FormItem formItem;

        for (int i = 0; i<3; i++) {
            formItem = new FormItem();

            switch (i) {
                case 0:
                    formItem.getInput().setId(FormCustomerViewHolder.FORMIDENTITY_ID_FIRSTNAME);
                    formItem.getInput().setName("Prénom");
                    formItem.getInput().setHint("Prénom");
                    break;
                case 1:
                    formItem.getInput().setId(FormCustomerViewHolder.FORMIDENTITY_ID_LASTNAME);
                    formItem.getInput().setName("Nom");
                    formItem.getInput().setHint("Nom");
                    break;
                case 2:
                    formItem.getInput().setId(FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL);
                    formItem.getInput().setName("Email");
                    formItem.getInput().setHint("Email");
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
        for (FormItemInput input : formCustomer.formIdentity.getInputs()) {
            switch (input.getId()) {
                case FormCustomerViewHolder.FORMIDENTITY_ID_FIRSTNAME:
                    input.setValue(customer.getFirstName());
                    break;
                case FormCustomerViewHolder.FORMIDENTITY_ID_LASTNAME:
                    input.setValue(customer.getLastName());
                    break;
                case FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL:
                    input.setValue(customer.getEmail());
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
        String email = formCustomer.formIdentity.findInputById(FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL).getValue();

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
        for (FormItemInput input : formCustomer.formIdentity.getInputs()) {
            switch (input.getId()) {
                case FormCustomerViewHolder.FORMIDENTITY_ID_FIRSTNAME:
                    customer.setFirstName(input.getValue());
                    break;
                case FormCustomerViewHolder.FORMIDENTITY_ID_LASTNAME:
                    customer.setLastName(input.getValue());
                    break;
                case FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL:
                    customer.setEmail(input.getValue());
                    break;
            }
        }
    }

    @Override
    public void execute(final FormItemInput input) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(input.getName());

        final EditText editText = new EditText(this);
        if (input.getValue() != null) {
            editText.setText(input.getValue());
        } else {
            editText.setHint(input.getHint());
        }

        switch (input.getId()) {
            case FormCustomerViewHolder.FORMIDENTITY_ID_FIRSTNAME:
            case FormCustomerViewHolder.FORMIDENTITY_ID_LASTNAME:
            case FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL:
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        input.setValue(editText.getText().toString());
                    }
                });

                break;
            case FormCustomerViewHolder.FORMIDENTITY_ID_PASSWORD:
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        input.setValue(editText.getText().toString());
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