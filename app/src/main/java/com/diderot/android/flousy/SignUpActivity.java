package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import flousy.data.DataManager;
import flousy.data.SessionManager;
import flousy.data.UserManager;
import flousy.user.User;
import flousy.gui.activitybar.ActivityBarType;
import flousy.gui.activitybar.TitledActivityBar;
import flousy.gui.widget.CustomDialogBuilder;
import flousy.gui.listener.CustomOnTouchListener;
import flousy.user.Validator;

public class SignUpActivity extends MyActivity {

    private static int SIGNUP_TIME_OUT = 2000;
    private Handler handler;
    private Runnable runnable;

    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;
    private CheckBox checkBoxValidation;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Enable ActionBar
        setActionBarEnabled(true);
        setActionBarDisplayHomeAsUpEnabled(true);

        //Set ActivityBar
        TitledActivityBar activityBar = (TitledActivityBar) createActivityBar(ActivityBarType.TITLEDBAR);
        activityBar.setTitle(getResources().getString(R.string.signup_titledbar_title));

        //Set ActivityContent
        View view = createActivityContent(R.layout.layout_activity_signup);

        this.editTextFirstName = (EditText) findViewById(R.id.signup_edittext_firstname);
        this.editTextLastName = (EditText) findViewById(R.id.signup_edittext_lastname);
        this.editTextEmail = (EditText) findViewById(R.id.signup_edittext_email);
        this.editTextPassword = (EditText) findViewById(R.id.signup_edittext_password);
        this.editTextConfirmPassword = (EditText) findViewById(R.id.signup_edittext_confirmpassword);

        this.checkBoxValidation = (CheckBox) findViewById(R.id.signup_checkbox_validation);

        this.buttonSave = (Button) findViewById(R.id.signup_button_save);
        this.buttonSave.setBackgroundColor(getActivityColor().getColor());
        this.buttonSave.setOnTouchListener(new CustomOnTouchListener(getActivityColor()));
        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        this.buttonSave.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.performClick();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            default :
                return super.onOptionsItemSelected(item);
        }
    }

    public void signUp() {
        UserManager manager = new UserManager(this);
        DataManager data = (DataManager) manager.getManager(UserManager.TYPE_DATA);
        SessionManager session = (SessionManager) manager.getManager(UserManager.TYPE_SESSION);

        String firstName = this.editTextFirstName.getEditableText().toString();
        String lastName = this.editTextLastName.getEditableText().toString();
        String email = this.editTextEmail.getEditableText().toString();
        String password = this.editTextPassword.getEditableText().toString();
        String confirmPassword = this.editTextConfirmPassword.getEditableText().toString();

        String phoneNumber = "0000";
        Drawable image = null;

        User user = new User(firstName, lastName, phoneNumber, email, password, image);

        boolean signed = false;
        boolean valid = Validator.validUser(user);
        if(valid == true) {
            Boolean checkboxValidation = this.checkBoxValidation.isChecked();
            if(confirmPassword.compareTo(password) == 0 && checkboxValidation == true) {
                signed = data.signUp(user);
            }
        }

        if(signed == false) {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
            builder.setTitle(R.string.signup_alertdialog_title_error)
                    .setMessage(R.string.signup_alertdialog_message_error)
                    .setNeutralButton(null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
            final AlertDialog dialog = builder.create();

            final Intent loginActivity = new Intent(this, LogInActivity.class);
            loginActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            loginActivity.putExtra("CLOSE", true);
            loginActivity.putExtra("NEW_USER_FIRSTNAME", user.getFirstName());

            this.runnable = new Runnable() {

                @Override
                public void run() {
                    startActivity(loginActivity);
                    dialog.dismiss();
                    finish();
                }
            };

            this.handler = new Handler();
            dialog.show();
            session.connect(user.getEmail(), user.getPassword());
            this.handler.postDelayed(this.runnable, SIGNUP_TIME_OUT);
        }
    }
}