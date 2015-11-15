package com.example.flousy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import flousy.bean.Account;
import flousy.constant.Extra;
import flousy.db.DataAccessor;
import flousy.db.DataAccessorFactory;
import flousy.form.FormValidator;
import flousy.gui.widget.dialog.CustomDialog;
import flousy.gui.widget.dialog.CustomDialogBuilder;
import flousy.session.Session;

public class SignActivity extends ActionBarActivity {

    private class FormAccountViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;
        public CheckBox checkBoxValidTerms;
    }

    private static final int SIGNUP_TIMEOUT = 2000;

    private FormAccountViewHolder formAccount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign);

        createFormAccount();
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

    private void createFormAccount() {
        this.formAccount = new FormAccountViewHolder();

        this.formAccount.editTextFirstName = (EditText) findViewById(R.id.sign_form_user_edittext_firstname);
        this.formAccount.editTextLastName = (EditText) findViewById(R.id.sign_form_user_edittext_lastname);
        this.formAccount.editTextEmail = (EditText) findViewById(R.id.sign_form_user_edittext_email);
        this.formAccount.editTextPassword = (EditText) findViewById(R.id.sign_form_user_edittext_password);
        this.formAccount.editTextConfirmPassword = (EditText) findViewById(R.id.sign_form_user_edittext_confirmpassword);
        this.formAccount.checkBoxValidTerms = (CheckBox) findViewById(R.id.sign_form_user_checkbox_terms);
    }

    private void signUp() {
        String[] tabFormErrors = validFormAccount();

        if (tabFormErrors.length == 0) {
            Account account = getAccountFromForm();

            DataAccessor dao = DataAccessorFactory.get();

            if (!dao.containsAccountByUserEmail(account.getUserEmail())) {
                dao.insertAccount(account);

                Session.logIn(account.getUserEmail(), account.getUserPassword());

                goToHomeActivityWithWelcome(account.getUserFirstName());
            } else {
                CustomDialog.showOkDialog(this, "Error sign", "Email (" + account.getUserEmail() + ") already exists");
            }
        } else {
            CustomDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
        }
    }

    private String[] validFormAccount() {
        FormValidator formValidator = new FormValidator();

        String firstName = this.formAccount.editTextFirstName.getText().toString().trim();
        String lastName = this.formAccount.editTextLastName.getText().toString().trim();
        String email = this.formAccount.editTextEmail.getText().toString().trim();
        String password = this.formAccount.editTextPassword.getText().toString().trim();
        String confirmPassword = this.formAccount.editTextConfirmPassword.getText().toString().trim();
        Boolean validTerms = this.formAccount.checkBoxValidTerms.isChecked();

        formValidator.validName(firstName, "firstname");
        formValidator.validName(lastName, "lastname");
        formValidator.validEmail(email, "email");
        formValidator.validPassword(password, "password");
        formValidator.validConfirmPassword(password, confirmPassword, "confirmpassword");
        formValidator.validCheckBox(validTerms, "terms");

        return formValidator.getErrors();
    }

    private Account getAccountFromForm() {
        Account account = new Account();

        String firstName = this.formAccount.editTextFirstName.getText().toString().trim();
        String lastName = this.formAccount.editTextLastName.getText().toString().trim();
        String email = this.formAccount.editTextEmail.getText().toString().trim();
        String password = this.formAccount.editTextPassword.getText().toString().trim();

        account.setUserFirstName(firstName);
        account.setUserLastName(lastName);
        account.setUserEmail(email);
        account.setUserPassword(password);

        return account;
    }

    private void goToHomeActivityWithWelcome(String userFirstName) {
        CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
        final AlertDialog dialog = builder.create();

        final Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Extra.WELCOME, true);
        intent.putExtra(Extra.USER_FIRSTNAME, userFirstName);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
                dialog.dismiss();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, SIGNUP_TIMEOUT);

        dialog.show();
    }
}