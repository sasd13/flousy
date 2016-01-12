package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.session.Session;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.db.CustomerDAO;
import com.sasd13.flousy.db.sqlite.SQLiteDAO;
import com.sasd13.javaex.db.IDAO;

public class SignActivity extends ActionBarActivity {

    private class FormCustomerViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;
        public CheckBox checkBoxValidTerms;
    }

    private static final int SIGNUP_TIMEOUT = 2000;

    private FormCustomerViewHolder formCustomer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign);

        createFormCustomer();
    }

    private void createFormCustomer() {
        this.formCustomer = new FormCustomerViewHolder();

        this.formCustomer.editTextFirstName = (EditText) findViewById(R.id.sign_form_user_edittext_firstname);
        this.formCustomer.editTextLastName = (EditText) findViewById(R.id.sign_form_user_edittext_lastname);
        this.formCustomer.editTextEmail = (EditText) findViewById(R.id.sign_form_user_edittext_email);
        this.formCustomer.editTextPassword = (EditText) findViewById(R.id.sign_form_user_edittext_password);
        this.formCustomer.editTextConfirmPassword = (EditText) findViewById(R.id.sign_form_user_edittext_confirmpassword);
        this.formCustomer.checkBoxValidTerms = (CheckBox) findViewById(R.id.sign_form_user_checkbox_terms);
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

        IDAO dao = SQLiteDAO.getInstance();

        try {
            dao.open();

            if (((CustomerDAO) dao.getEntityDAO(Customer.class)).selectByEmail(customer.getEmail()) != null) {
                performSignUp(customer, dao);

                Session.logIn(customer.getId());

                goToHomeActivityWithWelcome(customer.getFirstName());
            } else {
                CustomDialog.showOkDialog(this, "Error sign", "Email (" + customer.getEmail() + ") already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }

    private Customer getCustomerFromForm() {
        Customer customer = new Customer();

        String firstName = this.formCustomer.editTextFirstName.getText().toString().trim();
        String lastName = this.formCustomer.editTextLastName.getText().toString().trim();
        String email = this.formCustomer.editTextEmail.getText().toString().trim();
        String password = this.formCustomer.editTextPassword.getText().toString().trim();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassword(password);

        return customer;
    }

    private void performSignUp(Customer customer, IDAO dAO) {
        dAO.getEntityDAO(Customer.class).insert(customer);
        dAO.getEntityDAO(Account.class).insert(customer.getAccount());
    }

    private void goToHomeActivityWithWelcome(String userFirstName) {
        final WaitDialog waitDialog = new WaitDialog(this);

        final Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Extra.WELCOME, true);
        intent.putExtra(Extra.USER_FIRSTNAME, userFirstName);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
                waitDialog.dismiss();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, SIGNUP_TIMEOUT);

        waitDialog.show();
    }
}