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
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.form.Form;
import com.sasd13.androidex.gui.widget.recycler.form.FormItem;
import com.sasd13.androidex.gui.widget.recycler.form.FormItemText;
import com.sasd13.androidex.gui.widget.recycler.form.FormItemToggle;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.LayeredPersistor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingActivity extends MotherActivity implements RecyclerItem.ActionListener {

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

        formCustomer.formIdentity = new Form((RecyclerView) findViewById(R.id.setting_form_user_recyclerview_identity));
        formCustomer.formIdentity.setScrollingDisabled(true);

        addFormIdentityItems();
    }

    private void addFormIdentityItems() {
        FormItem formItem;

        for (int i = 0; i<=3; i++) {
            switch (i) {
                case 0:
                    formItem = new FormItemText();
                    formItem.setId(FormCustomerViewHolder.FORMIDENTITY_ID_FIRSTNAME);
                    formItem.setLabel("Prénom");
                    break;
                case 1:
                    formItem = new FormItemText();
                    formItem.setId(FormCustomerViewHolder.FORMIDENTITY_ID_LASTNAME);
                    formItem.setLabel("Nom");
                    break;
                case 2:
                    formItem = new FormItemText();
                    formItem.setId(FormCustomerViewHolder.FORMIDENTITY_ID_EMAIL);
                    formItem.setLabel("Email");
                    break;
                case 3:
                    formItem = new FormItemToggle();
                    formItem.setId(-1);
                    formItem.setLabel("Activer compte");
                    break;
                default:
                    formItem = new FormItem();
                    break;
            }

            formItem.setOnClickListener(this);

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
        for (FormItem formItem : formCustomer.formIdentity.getItems()) {
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
                default:
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
        for (FormItem formItem : formCustomer.formIdentity.getItems()) {
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
    public void doAction(RecyclerItem recyclerItem) {
        final FormItem formItem = (FormItem) recyclerItem;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(formItem.getLabel());

        if (formItem instanceof FormItemText) {
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

                    break;
                case FormCustomerViewHolder.FORMIDENTITY_ID_PASSWORD:
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    break;
                default:

                    break;
            }

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ((FormItemText) formItem).getInput().setValue(editText.getText().toString());
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setView(editText);

            builder.show();
        } else if (formItem instanceof FormItemToggle) {
            ((FormItemToggle) formItem).getFormInput().setValue(((FormItemToggle) formItem).isChecked());

            Toast.makeText(this, String.valueOf(((FormItemToggle) formItem).isChecked()), Toast.LENGTH_SHORT).show();
        }
    }
}