package com.example.flousy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import flousy.bean.Customer;
import flousy.db.DAO;
import flousy.db.DAOFactory;
import flousy.form.FormValidator;
import flousy.gui.widget.dialog.CustomDialog;
import flousy.session.Session;

public class SettingActivity extends MotherActivity {

    private class FormCustomerViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail;
    }

    private FormCustomerViewHolder formCustomer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        createFormCustomer();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Customer customer = DAOFactory.get().selectCustomer(Session.getCustomerId());

        fillFormCustomer(customer);
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

    private void createFormCustomer() {
        this.formCustomer = new FormCustomerViewHolder();

        this.formCustomer.editTextFirstName = (EditText) findViewById(R.id.setting_form_user_edittext_firstname);
        this.formCustomer.editTextLastName = (EditText) findViewById(R.id.setting_form_user_edittext_lastname);
        this.formCustomer.editTextEmail = (EditText) findViewById(R.id.setting_form_user_edittext_email);
    }

    private void fillFormCustomer(Customer customer) {
        this.formCustomer.editTextFirstName.setText(customer.getFirstName(), TextView.BufferType.EDITABLE);
        this.formCustomer.editTextLastName.setText(customer.getLastName(), TextView.BufferType.EDITABLE);
        this.formCustomer.editTextEmail.setText(customer.getEmail(), TextView.BufferType.EDITABLE);
    }

    private void updateCustomer() {
        String[] tabFormErrors = validFormCustomer();

        if (tabFormErrors.length == 0) {
            String email = this.formCustomer.editTextEmail.getText().toString().trim();

            DAO dao = DAOFactory.get();

            if (!dao.containsCustomerByEmail(email)) {
                Customer customer = dao.selectCustomer(Session.getCustomerId());

                editCustomerWithForm(customer);
                dao.updateCustomer(customer);
            } else {
                Customer customer = dao.selectCustomerByEmail(email);

                if (customer.getId() == Session.getCustomerId()) {
                    editCustomerWithForm(customer);
                    dao.updateCustomer(customer);
                } else {
                    CustomDialog.showOkDialog(this, "Error update", "Email (" + email + ") already exists");
                }
            }
        } else {
            CustomDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
        }
    }

    private String[] validFormCustomer() {
        FormValidator formValidator = new FormValidator();

        String firstName = this.formCustomer.editTextFirstName.getText().toString().trim();
        String lastName = this.formCustomer.editTextLastName.getText().toString().trim();
        String email = this.formCustomer.editTextEmail.getText().toString().trim();

        formValidator.validName(firstName, "firstname");
        formValidator.validName(lastName, "lastname");
        formValidator.validEmail(email, "email");

        return formValidator.getErrors();
    }

    private void editCustomerWithForm(Customer customer) {
        Customer customerFromForm = getCustomerFromForm();

        customer.setFirstName(customerFromForm.getFirstName());
        customer.setLastName(customerFromForm.getLastName());
        customer.setEmail(customerFromForm.getEmail());
    }

    private Customer getCustomerFromForm() {
        Customer customer = new Customer();

        String firstName = this.formCustomer.editTextFirstName.getText().toString().trim();
        String lastName = this.formCustomer.editTextLastName.getText().toString().trim();
        String email = this.formCustomer.editTextEmail.getText().toString().trim();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);

        return customer;
    }
}