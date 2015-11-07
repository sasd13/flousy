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

import flousy.bean.trading.CheckingTradingAccount;
import flousy.constant.Extra;
import flousy.bean.user.User;
import flousy.db.DBManager;
import flousy.db.DataAccessor;
import flousy.form.FormValidator;
import flousy.gui.widget.dialog.CustomDialog;
import flousy.gui.widget.dialog.CustomDialogBuilder;
import flousy.session.Session;

public class UserAccountActivity extends MotherActivity {

    private class FormViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;
        public CheckBox checkBoxValidTerms;
    }

    private static final int SIGNUP_TIME_OUT = 2000;

    private FormViewHolder formUser;
    private FormValidator formValidator;

    private DataAccessor dao = DBManager.getDao();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_account);

        this.formUser = new FormViewHolder();

        this.formUser.editTextFirstName = (EditText) findViewById(R.id.form_user_edittext_firstname);
        this.formUser.editTextLastName = (EditText) findViewById(R.id.form_user_edittext_lastname);
        this.formUser.editTextEmail = (EditText) findViewById(R.id.form_user_edittext_email);
        this.formUser.editTextPassword = (EditText) findViewById(R.id.form_user_edittext_password);
        this.formUser.editTextConfirmPassword = (EditText) findViewById(R.id.form_user_edittext_confirmpassword);
        this.formUser.checkBoxValidTerms = (CheckBox) findViewById(R.id.form_user_checkbox_validterms);
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
        if (validForm()) {
            User user = getUserFromForm();

            this.dao.open();

            boolean containsUserEmail = this.dao.containsUserByEmail(user.getEmail());

            if (!containsUserEmail) {
                createNewUser(user);

                this.dao.close();

                Session.logIn(user.getEmail(), user.getPassword());

                goToHomeActivity(user.getFirstName());
            } else {
                this.dao.close();

                CustomDialog.showOkDialog(this, "User error", "Email (" + user.getEmail() + ") already exists");
            }
        } else {
            CustomDialog.showOkDialog(this, "Form error", this.formValidator.getErrors()[0]);
        }
    }

    private boolean validForm() {
        this.formValidator = new FormValidator();

        String firstName = this.formUser.editTextFirstName.getEditableText().toString().trim();
        String lastName = this.formUser.editTextLastName.getEditableText().toString().trim();
        String email = this.formUser.editTextEmail.getEditableText().toString().trim();
        String password = this.formUser.editTextPassword.getEditableText().toString().trim();
        String confirmPassword = this.formUser.editTextConfirmPassword.getEditableText().toString().trim();
        Boolean validTerms = this.formUser.checkBoxValidTerms.isChecked();

        formValidator.validName(firstName, "firstname");
        formValidator.validName(lastName, "lastname");
        formValidator.validEmail(email, "email");
        formValidator.validPassword(password, "password");
        formValidator.validConfirmPassword(password, confirmPassword, "confirmpassword");
        formValidator.validCheckBox(validTerms, "terms");

        return formValidator.hasValidated();
    }

    private User getUserFromForm() {
        User user = new User();

        String firstName = this.formUser.editTextFirstName.getEditableText().toString().trim();
        String lastName = this.formUser.editTextLastName.getEditableText().toString().trim();
        String email = this.formUser.editTextEmail.getEditableText().toString().trim();
        String password = this.formUser.editTextPassword.getEditableText().toString().trim();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }

    private void createNewUser(User user) {
        CheckingTradingAccount checkingTradingAccount = new CheckingTradingAccount();

        this.dao.insertUser(user);
        this.dao.insertAccount(checkingTradingAccount, user);
    }

    private void goToHomeActivity(String firstName) {
        CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
        final AlertDialog dialog = builder.create();

        final Intent intent = new Intent(this, UserLogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Extra.CLOSE, true);
        intent.putExtra(Extra.USER_FIRSTNAME, firstName);

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
    }
}