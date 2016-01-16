package com.sasd13.flousy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.session.Session;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.db.CustomerDAO;
import com.sasd13.flousy.db.DAOFactory;
import com.sasd13.javaex.db.IDAO;

public class SettingActivity extends MotherActivity {

    private class FormCustomerViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail;
    }

    private FormCustomerViewHolder formCustomer;

    private IDAO dao = DAOFactory.make();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        createFormCustomer();
    }

    private void createFormCustomer() {
        formCustomer = new FormCustomerViewHolder();

        formCustomer.editTextFirstName = (EditText) findViewById(R.id.setting_form_user_edittext_firstname);
        formCustomer.editTextLastName = (EditText) findViewById(R.id.setting_form_user_edittext_lastname);
        formCustomer.editTextEmail = (EditText) findViewById(R.id.setting_form_user_edittext_email);
    }

    @Override
    protected void onStart() {
        super.onStart();

        dao.open();
        Customer customer = (Customer) dao.getEntityDAO(Customer.class).select(Session.getId());
        dao.close();

        fillFormCustomer(customer);
    }

    private void fillFormCustomer(Customer customer) {
        formCustomer.editTextFirstName.setText(customer.getFirstName(), TextView.BufferType.EDITABLE);
        formCustomer.editTextLastName.setText(customer.getLastName(), TextView.BufferType.EDITABLE);
        formCustomer.editTextEmail.setText(customer.getEmail(), TextView.BufferType.EDITABLE);
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
        String email = formCustomer.editTextEmail.getText().toString().trim();

        dao.open();

        Customer customer = ((CustomerDAO) dao.getEntityDAO(Customer.class)).selectByEmail(email);
        if (customer == null) {
            customer = (Customer) dao.getEntityDAO(Customer.class).select(Session.getId());

            performUpdateCustomer(customer);
        } else {
            if (customer.getId() == Session.getId()) {
                performUpdateCustomer(customer);
            } else {
                CustomDialog.showOkDialog(this, "Error update", "Email (" + email + ") already exists");
            }
        }

        dao.close();
    }

    private void performUpdateCustomer(Customer customer) {
        editCustomerWithForm(customer);
        dao.getEntityDAO(Customer.class).update(customer);
        Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
    }

    private void editCustomerWithForm(Customer customer) {
        customer.setFirstName(formCustomer.editTextFirstName.getText().toString().trim());
        customer.setLastName(formCustomer.editTextLastName.getText().toString().trim());
        customer.setEmail(formCustomer.editTextEmail.getText().toString().trim());
    }
}