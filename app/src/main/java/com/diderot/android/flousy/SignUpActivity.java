package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import flousy.gui.actionbar.ActionBar;
import flousy.util.DataManager;
import flousy.util.SessionManager;
import flousy.content.user.User;
import flousy.gui.widget.CustomDialogBuilder;
import flousy.gui.listener.CustomOnTouchListener;
import flousy.util.FormValidator;

public class SignUpActivity extends MotherActivity {

    private static int SIGNUP_TIME_OUT = 2000;
    private Handler handler;
    private Runnable runnable;

    private class ViewHolder {
        public EditText firstNameEditText, lastNameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
        public CheckBox validCheckBox;
        public Button saveButton;
    }

    private ViewHolder form;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set ActivityContent
        setContentView(R.layout.userform_layout);

        //Set CustomActionBar
        ActionBar actionBar = getCustomActionBar();
        actionBar.getTitleView().setText(R.string.activity_signup_name);

        //Disable Drawer
        actionBar.setActionDrawerButtonEnabled(false);
        getDrawer().setEnabled(false);

        //Set User form
        this.form = new ViewHolder();

        this.form.firstNameEditText = (EditText) findViewById(R.id.userform_edittext_firstname);
        this.form.lastNameEditText = (EditText) findViewById(R.id.userform_edittext_lastname);
        this.form.emailEditText = (EditText) findViewById(R.id.userform_edittext_email);
        this.form.passwordEditText = (EditText) findViewById(R.id.userform_edittext_password);

        this.form.validCheckBox = (CheckBox) findViewById(R.id.userform_checkbox);
        TextView validCheckBoxTextView = (TextView) findViewById(R.id.userform_textview_validcheckbox);
        validCheckBoxTextView.setText(R.string.signup_userform_textview_validcheckbox_validation);

        this.form.saveButton = (Button) findViewById(R.id.userform_button);
        this.form.saveButton.setText(R.string.settings_userform_button_logout);
        this.form.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        this.form.saveButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.performClick();
                return false;
            }
        });

        //Customize activity
        customizeColor();
        customizeText();
        customizeDimensions();
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void customizeColor() {
        super.customizeColor();

        if(getContentView() != null) {
            this.form.saveButton.setBackgroundColor(getActivityColor());
            this.form.saveButton.setOnTouchListener(new CustomOnTouchListener(getActivityColor()));
        }
    }

    public void signUp() {
        SessionManager session = new SessionManager(this);
        DataManager data = new DataManager(this);

        String firstName = this.form.firstNameEditText.getEditableText().toString().trim();
        String lastName = this.form.lastNameEditText.getEditableText().toString().trim();
        String email = this.form.emailEditText.getEditableText().toString().trim();
        String password = this.form.passwordEditText.getEditableText().toString().trim();
        String confirmPassword = this.form.confirmPasswordEditText.getEditableText().toString().trim();
        Boolean validCheckBox = this.form.validCheckBox.isChecked();

        String phoneNumber = "0000";
        Drawable image = null;

        User user = new User(firstName, lastName, phoneNumber, email, password, image);

        boolean signed = false;
        boolean valid = FormValidator.validUser(user);
        if(valid == true && confirmPassword.compareTo(password) == 0 && validCheckBox == true) {
            signed = data.signUp(user);
        }

        if(signed == false) {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
            builder.setTitle(R.string.signup_alertdialog_signup_title_error)
                    .setMessage(R.string.signup_alertdialog_signup_message_error)
                    .setNeutralButton(null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
            final AlertDialog dialog = builder.create();

            final Intent intent = new Intent(this, LogInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("CLOSE", true);
            intent.putExtra("NEW_USER_FIRSTNAME", user.getFirstName());

            this.runnable = new Runnable() {

                @Override
                public void run() {
                    startActivity(intent);
                    dialog.dismiss();
                    finish();
                }
            };

            this.handler = new Handler();
            this.handler.postDelayed(this.runnable, SIGNUP_TIME_OUT);

            dialog.show();
            session.connect(user.getEmail(), user.getPassword());
        }
    }
}