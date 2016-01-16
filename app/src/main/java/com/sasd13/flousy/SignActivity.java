package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.session.Session;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.db.CustomerDAO;
import com.sasd13.flousy.db.DAOFactory;
import com.sasd13.javaex.db.IDAO;

public class SignActivity extends ActionBarActivity {

    private class FormCustomerViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;
        public CheckBox checkBoxValidTerms;
    }

    private static final int TIMEOUT = 2000;

    private FormCustomerViewHolder formCustomer;

    private IDAO dao = DAOFactory.make();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign);
        createFormCustomer();
    }

    private void createFormCustomer() {
        formCustomer = new FormCustomerViewHolder();

        formCustomer.editTextFirstName = (EditText) findViewById(R.id.sign_form_user_edittext_firstname);
        formCustomer.editTextLastName = (EditText) findViewById(R.id.sign_form_user_edittext_lastname);
        formCustomer.editTextEmail = (EditText) findViewById(R.id.sign_form_user_edittext_email);
        formCustomer.editTextPassword = (EditText) findViewById(R.id.sign_form_user_edittext_password);
        formCustomer.editTextConfirmPassword = (EditText) findViewById(R.id.sign_form_user_edittext_confirmpassword);
        formCustomer.checkBoxValidTerms = (CheckBox) findViewById(R.id.sign_form_user_checkbox_terms);
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
                signUp();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void signUp() {
        String[] tabFormErrors = validFormCustomer();

        if (true) {
            tryToPerformSignUp();
        } else {
            CustomDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
        }
    }

    private String[] validFormCustomer() {
        //TODO

        return null;
    }

    private void tryToPerformSignUp() {
        Customer customer = getCustomerFromForm();

        dao.open();

        CustomerDAO customerDAO = (CustomerDAO) dao.getEntityDAO(Customer.class);
        if (customerDAO.selectByEmail(customer.getEmail()) == null) {
            performSignUp(customer);

            dao.close();

            Session.logIn(customer.getId());
            goToHomeActivityWithWelcome(customer.getFirstName());
        } else {
            dao.close();

            CustomDialog.showOkDialog(this, "Error sign", "Email (" + customer.getEmail() + ") already exists");
        }
    }

    private Customer getCustomerFromForm() {
        Customer customer = new Customer();

        customer.setFirstName(formCustomer.editTextFirstName.getText().toString().trim());
        customer.setLastName(formCustomer.editTextLastName.getText().toString().trim());
        customer.setEmail(formCustomer.editTextEmail.getText().toString().trim());
        customer.setPassword(formCustomer.editTextPassword.getText().toString().trim());

        return customer;
    }

    private void performSignUp(Customer customer) {
        dao.getEntityDAO(Customer.class).insert(customer);
        dao.getEntityDAO(Account.class).insert(customer.getAccount());
    }

    private void goToHomeActivityWithWelcome(final String firstName) {
        final WaitDialog waitDialog = new WaitDialog(this);

        TaskPlanner taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SignActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Extra.WELCOME, true);
                intent.putExtra(Extra.FIRSTNAME, firstName);

                startActivity(intent);
                waitDialog.dismiss();
            }
        }, TIMEOUT);

        taskPlanner.start();
        waitDialog.show();
    }
}