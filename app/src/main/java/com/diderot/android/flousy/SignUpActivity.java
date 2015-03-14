package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import flousy.user.UserManager;
import flousy.user.User;
import flousy.util.activitybar.ActivityBarFactory;
import flousy.util.activitybar.TitledActivityBar;
import flousy.util.widget.CustomDialogBuilder;
import flousy.util.widget.CustomOnTouchListener;
import flousy.user.Validator;

public class SignUpActivity extends MyActivity {

    private static int SIGNUP_TIME_OUT = 3000;
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
        TitledActivityBar activityBar = (TitledActivityBar) this.createActivityBar(ActivityBarFactory.TYPE_TITLEDACTIVITYBAR);
        activityBar.setTitle(getResources().getString(R.string.signup_titledbar_title));

        //Set ActivityContent
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(R.layout.layout_activity_signup);
        ViewGroup view = (ViewGroup) viewStub.inflate();

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
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void signUp() {
        UserManager manager = new UserManager(this);
        boolean signed = false;

        String firstName = this.editTextFirstName.getEditableText().toString();
        String lastName = this.editTextLastName.getEditableText().toString();
        String phoneNumber = "0000";
        String email = this.editTextEmail.getEditableText().toString();
        String password = this.editTextPassword.getEditableText().toString();
        String confirmPassword = this.editTextConfirmPassword.getEditableText().toString();

        Boolean checkboxValidation = this.checkBoxValidation.isChecked();

        User user = new User(firstName, lastName, phoneNumber, email, password, null);

        boolean valid = Validator.validUser(user);
        if(valid == true) {
            if(confirmPassword.compareTo(password) == 0 && checkboxValidation == true) {
                signed = manager.signUp(user);
            }
        }

        CustomDialogBuilder builder;

        if(signed == false) {
            builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
            builder.setTitle(R.string.signup_alertdialog_title_error)
                    .setMessage(R.string.signup_alertdialog_message_error)
                    .setNeutralButton(null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
            final AlertDialog dialog = builder.create();

            final Intent menuActivity = new Intent(this, MenuActivity.class);
            menuActivity.putExtra("NEW_USER", true);

            this.runnable = new Runnable() {

                @Override
                public void run() {
                    startActivity(menuActivity);
                    dialog.dismiss();
                    finish();
                }
            };

            this.handler = new Handler();
            dialog.show();
            manager.connect(user.getEmail(), user.getPassword());
            this.handler.postDelayed(this.runnable, SIGNUP_TIME_OUT);
        }
    }
}