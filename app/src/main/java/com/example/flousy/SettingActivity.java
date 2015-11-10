package com.example.flousy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import flousy.beans.core.User;
import flousy.db.DBManager;
import flousy.db.DataAccessor;
import flousy.form.FormValidator;
import flousy.gui.widget.dialog.CustomDialog;
import flousy.session.Session;

public class SettingActivity extends MotherActivity {

    private class FormUserViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail;
    }

    private DataAccessor dao;
    private long userId;
    private FormUserViewHolder formUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        createFormUser();
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.dao = DBManager.getDao();

        this.userId = Long.parseLong(Session.getUserId());

        fillFormUser();
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
                updateUser();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createFormUser() {
        this.formUser = new FormUserViewHolder();

        this.formUser.editTextFirstName = (EditText) findViewById(R.id.setting_form_user_edittext_firstname);
        this.formUser.editTextLastName = (EditText) findViewById(R.id.setting_form_user_edittext_lastname);
        this.formUser.editTextEmail = (EditText) findViewById(R.id.setting_form_user_edittext_email);
    }

    private void fillFormUser() {
        User user = this.dao.selectUser(this.userId);

        this.formUser.editTextFirstName.setText(user.getFirstName(), TextView.BufferType.EDITABLE);
        this.formUser.editTextLastName.setText(user.getLastName(), TextView.BufferType.EDITABLE);
        this.formUser.editTextEmail.setText(user.getEmail(), TextView.BufferType.EDITABLE);
    }

    private void updateUser() {
        String[] tabFormErrors = validFormUser();

        if (tabFormErrors.length == 0) {
            String email = this.formUser.editTextEmail.getText().toString().trim();

            boolean containsUserEmail = this.dao.containsUserByEmail(email);

            if (!containsUserEmail) {
                User user = editUserWithForm();

                this.dao.updateUser(user);
            } else {
                CustomDialog.showOkDialog(this, "Update error", "Email (" + email + ") already exists");
            }
        } else {
            CustomDialog.showOkDialog(this, "Form error", tabFormErrors[0]);
        }
    }

    private String[] validFormUser() {
        FormValidator formValidator = new FormValidator();

        String firstName = this.formUser.editTextFirstName.getText().toString().trim();
        String lastName = this.formUser.editTextLastName.getText().toString().trim();
        String email = this.formUser.editTextEmail.getText().toString().trim();

        formValidator.validName(firstName, "firstname");
        formValidator.validName(lastName, "lastname");
        formValidator.validEmail(email, "email");

        return formValidator.getErrors();
    }

    private User editUserWithForm() {
        User user = this.dao.selectUser(this.userId);

        User userFromForm = getUserFromForm();
        user.setFirstName(userFromForm.getFirstName());
        user.setLastName(userFromForm.getLastName());
        user.setEmail(userFromForm.getEmail());

        return user;
    }

    private User getUserFromForm() {
        User user = new User();

        String firstName = this.formUser.editTextFirstName.getText().toString().trim();
        String lastName = this.formUser.editTextLastName.getText().toString().trim();
        String email = this.formUser.editTextEmail.getText().toString().trim();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        return user;
    }
}