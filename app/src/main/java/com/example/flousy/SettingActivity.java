package com.example.flousy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import flousy.beans.Account;
import flousy.db.DataAccessor;
import flousy.db.DataAccessorFactory;
import flousy.form.FormValidator;
import flousy.gui.widget.dialog.CustomDialog;
import flousy.session.Session;

public class SettingActivity extends MotherActivity {

    private class FormAccountViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail;
    }

    private FormAccountViewHolder formAccount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        createFormAccount();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Account account = DataAccessorFactory.get().selectAccount(Session.getAccountId());

        fillFormAccount(account);
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
                updateAccount();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createFormAccount() {
        this.formAccount = new FormAccountViewHolder();

        this.formAccount.editTextFirstName = (EditText) findViewById(R.id.setting_form_user_edittext_firstname);
        this.formAccount.editTextLastName = (EditText) findViewById(R.id.setting_form_user_edittext_lastname);
        this.formAccount.editTextEmail = (EditText) findViewById(R.id.setting_form_user_edittext_email);
    }

    private void fillFormAccount(Account account) {
        this.formAccount.editTextFirstName.setText(account.getUserFirstName(), TextView.BufferType.EDITABLE);
        this.formAccount.editTextLastName.setText(account.getUserLastName(), TextView.BufferType.EDITABLE);
        this.formAccount.editTextEmail.setText(account.getUserEmail(), TextView.BufferType.EDITABLE);
    }

    private void updateAccount() {
        String[] tabFormErrors = validFormAccount();

        if (tabFormErrors.length == 0) {
            String email = this.formAccount.editTextEmail.getText().toString().trim();

            DataAccessor dao = DataAccessorFactory.get();

            if (!dao.containsAccountByUserEmail(email)) {
                Account account = dao.selectAccount(Session.getAccountId());

                editAccountWithForm(account);
                dao.updateAccount(account);
            } else {
                Account account = dao.selectAccountByUserEmail(email);

                if (account.getId() == Session.getAccountId()) {
                    editAccountWithForm(account);
                    dao.updateAccount(account);
                } else {
                    CustomDialog.showOkDialog(this, "Update error", "Email (" + email + ") already exists");
                }
            }
        } else {
            CustomDialog.showOkDialog(this, "Form error", tabFormErrors[0]);
        }
    }

    private String[] validFormAccount() {
        FormValidator formValidator = new FormValidator();

        String firstName = this.formAccount.editTextFirstName.getText().toString().trim();
        String lastName = this.formAccount.editTextLastName.getText().toString().trim();
        String email = this.formAccount.editTextEmail.getText().toString().trim();

        formValidator.validName(firstName, "firstname");
        formValidator.validName(lastName, "lastname");
        formValidator.validEmail(email, "email");

        return formValidator.getErrors();
    }

    private void editAccountWithForm(Account account) {
        Account accountFromForm = getAccountFromForm();

        account.setUserFirstName(accountFromForm.getUserFirstName());
        account.setUserLastName(accountFromForm.getUserLastName());
        account.setUserEmail(accountFromForm.getUserEmail());
    }

    private Account getAccountFromForm() {
        Account account = new Account();

        String firstName = this.formAccount.editTextFirstName.getText().toString().trim();
        String lastName = this.formAccount.editTextLastName.getText().toString().trim();
        String email = this.formAccount.editTextEmail.getText().toString().trim();

        account.setUserFirstName(firstName);
        account.setUserLastName(lastName);
        account.setUserEmail(email);

        return account;
    }
}