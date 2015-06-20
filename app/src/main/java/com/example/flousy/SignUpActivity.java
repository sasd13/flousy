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
import flousy.content.Client;
import flousy.data.dao.DataAccessorManager;
import flousy.data.dao.accessor.DataAccessor;
import flousy.form.FormException;
import flousy.form.FormUserValidator;
import flousy.gui.widget.dialog.CustomDialog;
import flousy.gui.widget.dialog.CustomDialogBuilder;
import flousy.session.Session;
import flousy.util.IdGenerator;
import flousy.util.IdGeneratorType;

public class SignUpActivity extends MotherActivity {

    private class ViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;
        public CheckBox checkBoxValid;
    }

    private static final int SIGNUP_TIME_OUT = 2000;

    private ViewHolder formUser;

    private DataAccessor dao = DataAccessorManager.getDao();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        this.formUser = new ViewHolder();

        this.formUser.editTextFirstName = (EditText) findViewById(R.id.form_user_edittext_firstname);
        this.formUser.editTextLastName = (EditText) findViewById(R.id.form_user_edittext_lastname);
        this.formUser.editTextEmail = (EditText) findViewById(R.id.form_user_edittext_email);
        this.formUser.editTextPassword = (EditText) findViewById(R.id.form_user_edittext_password);
        this.formUser.editTextConfirmPassword = (EditText) findViewById(R.id.form_user_edittext_confirmpassword);
        this.formUser.checkBoxValid = (CheckBox) findViewById(R.id.form_user_checkbox_valid);
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
            Client client = validForm();

            this.dao.open();
            this.dao.insertClient(client);
            this.dao.close();

            goToHomeActivity(client);
        } catch (FormException e) {
            CustomDialog.showOkDialog(this, "Form error", e.getMessage());
        }
    }

    private Client validForm() throws FormException {
        Client client;

        String firstName = this.formUser.editTextFirstName.getEditableText().toString().trim();
        String lastName = this.formUser.editTextLastName.getEditableText().toString().trim();
        String email = this.formUser.editTextEmail.getEditableText().toString().trim();
        String password = this.formUser.editTextPassword.getEditableText().toString().trim();
        String confirmPassword = this.formUser.editTextConfirmPassword.getEditableText().toString().trim();
        Boolean checkBoxValid = this.formUser.checkBoxValid.isChecked();

        FormUserValidator.validForm(firstName, lastName, email, password, confirmPassword, checkBoxValid);

        client = new Client();
        client.setId(IdGenerator.get(this, IdGeneratorType.CLIENT));
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setEmail(email);
        client.setPassword(password);

        return client;
    }

    private void goToHomeActivity(Client client) {
        CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
        final AlertDialog dialog = builder.create();

        final Intent intent = new Intent(this, LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Extra.CLOSE, true);
        intent.putExtra(Extra.USER_FIRSTNAME, client.getFirstName());

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
        Session.logIn(client.getEmail(), client.getPassword());
    }
}