package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import flousy.beans.Utilisateurs;
import flousy.gui.actionbar.ActionBar;
import flousy.tool.DataManager;
import flousy.tool.Session;
import flousy.content.user.User;
import flousy.gui.widget.CustomDialogBuilder;
import flousy.tool.FormValidator;

public class SignUpActivity extends MotherActivity {

    private static int SIGNUP_TIME_OUT = 2000;
    private Handler handler;
    private Runnable runnable;

    private class ViewHolder {
        public EditText firstNameEditText, lastNameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
        public CheckBox validCheckBox;
    }

    private ViewHolder form;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //Set ActivityContent
        setContentView(R.layout.form_user_layout);

        //Set CustomActionBar
        ActionBar actionBar = getCustomActionBar();
        actionBar.getTitleView().setText(R.string.activity_signup_name);

        ImageButton buttonValid = actionBar.getActionFirstButton();
        buttonValid.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_accept));
        buttonValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        actionBar.setActionFirstButtonEnabled(true);

        //Disable Drawer
        actionBar.setActionDrawerButtonEnabled(false);
        getDrawer().setEnabled(false);

        //Set User form
        this.form = new ViewHolder();

        this.form.firstNameEditText = (EditText) findViewById(R.id.form_user_edittext_firstname);
        this.form.lastNameEditText = (EditText) findViewById(R.id.form_user_edittext_lastname);
        this.form.emailEditText = (EditText) findViewById(R.id.form_user_edittext_email);
        this.form.passwordEditText = (EditText) findViewById(R.id.form_user_edittext_password);
        this.form.confirmPasswordEditText = (EditText) findViewById(R.id.form_user_edittext_confirmpassword);

        this.form.validCheckBox = (CheckBox) findViewById(R.id.form_user_checkbox);
        TextView validCheckBoxTextView = (TextView) findViewById(R.id.form_user_textview_validcheckbox);
        validCheckBoxTextView.setText(R.string.signup_form_user_textview_validcheckbox_validation);

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

    public void signUp() {
        Session session = new Session(this);
        DataManager data = new DataManager(this);
        WebService webService = new WebService(this);
        String firstName = this.form.firstNameEditText.getEditableText().toString().trim();
        String lastName = this.form.lastNameEditText.getEditableText().toString().trim();
        String email = this.form.emailEditText.getEditableText().toString().trim();
        String password = this.form.passwordEditText.getEditableText().toString().trim();
        String confirmPassword = this.form.confirmPasswordEditText.getEditableText().toString().trim();
        Boolean validCheckBox = this.form.validCheckBox.isChecked();
        boolean signed = false;

        String phoneNumber = "0000";
        Drawable image = null;
        if(!webService.existUserHTTPPost(email)){
            Utilisateurs utilisateur = new Utilisateurs();
            utilisateur.setNom(firstName);
            utilisateur.setPrenom(lastName);
            utilisateur.setEmail(email);
            utilisateur.setPassword(password);
            utilisateur.setSalaire(1000);
            if(webService.inscriptionUserHTTPPost(utilisateur)){
                User  user = new User(firstName, lastName, phoneNumber,email, password, image);
                boolean valid = FormValidator.validUser(user);
                if(valid == true && confirmPassword.compareTo(password) == 0 && validCheckBox == true) {
                    signed = data.signUp(user);
                }
                CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
                final AlertDialog dialog = builder.create();

                final Intent intent = new Intent(this, LogInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(EXTRA_CLOSE, true);
                intent.putExtra(EXTRA_USER_FIRSTNAME, user.getFirstName());

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
                session.logIn(user.getEmail(), user.getPassword());
            }

        }else{
            signed = false;
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
            builder.setTitle(R.string.signup_alertdialog_signup_title_error)
                    .setMessage(R.string.signup_alertdialog_signup_message_error)
                    .setNeutralButton(null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

<<<<<<< HEAD
=======




>>>>>>> 3c36de6a2c620298634675416fe60adeec56a06d
}