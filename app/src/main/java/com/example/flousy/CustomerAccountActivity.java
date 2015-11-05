package com.example.flousy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import flousy.constant.Extra;
import flousy.bean.customer.Customer;
import flousy.db.DBManager;
import flousy.db.DBAccessor;
import flousy.form.FormCustomerValidator;
import flousy.form.FormException;
import flousy.gui.widget.dialog.CustomDialog;
import flousy.gui.widget.dialog.CustomDialogBuilder;
import flousy.session.Session;

public class CustomerAccountActivity extends MotherActivity {

    private class FormViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;
        public CheckBox checkBoxValid;
    }

    private static final int SIGNUP_TIME_OUT = 2000;

    private FormViewHolder formCustomer;

    private DBAccessor dao = DBManager.getDao();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_customer_account);

        this.formCustomer = new FormViewHolder();

        this.formCustomer.editTextFirstName = (EditText) findViewById(R.id.form_user_edittext_firstname);
        this.formCustomer.editTextLastName = (EditText) findViewById(R.id.form_user_edittext_lastname);
        this.formCustomer.editTextEmail = (EditText) findViewById(R.id.form_user_edittext_email);
        this.formCustomer.editTextPassword = (EditText) findViewById(R.id.form_user_edittext_password);
        this.formCustomer.editTextConfirmPassword = (EditText) findViewById(R.id.form_user_edittext_confirmpassword);
        this.formCustomer.checkBoxValid = (CheckBox) findViewById(R.id.form_user_checkbox_valid);
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
            case R.id.action_valid:
                signUp();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void signUp() {
        try {
            Customer customer = validForm();

            this.dao.open();
            this.dao.insertCustomer(customer);
            this.dao.close();

            goToHomeActivity(customer);
        } catch (FormException e) {
            CustomDialog.showOkDialog(this, "Form error", e.getMessage());
        }
    }

    private Customer validForm() throws FormException {
        Customer customer;

        String firstName = this.formCustomer.editTextFirstName.getEditableText().toString().trim();
        String lastName = this.formCustomer.editTextLastName.getEditableText().toString().trim();
        String email = this.formCustomer.editTextEmail.getEditableText().toString().trim();
        String password = this.formCustomer.editTextPassword.getEditableText().toString().trim();
        String confirmPassword = this.formCustomer.editTextConfirmPassword.getEditableText().toString().trim();
        Boolean checkBoxValid = this.formCustomer.checkBoxValid.isChecked();

        FormCustomerValidator.validForm(firstName, lastName, email, password, confirmPassword, checkBoxValid);

        customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassword(password);

        return customer;
    }

    private void goToHomeActivity(Customer customer) {
        CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
        final AlertDialog dialog = builder.create();

        final Intent intent = new Intent(this, CustomerLogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Extra.CLOSE, true);
        intent.putExtra(Extra.CUSTOMER_FIRSTNAME, customer.getFirstName());

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
                dialog.dismiss();
                finish();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, SIGNUP_TIME_OUT);

        dialog.show();
        Session.logIn(customer.getEmail(), customer.getPassword());
    }
}