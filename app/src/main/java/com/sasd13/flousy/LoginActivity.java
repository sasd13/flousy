package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.content.handler.LogInHandler;
import com.sasd13.flousy.util.SessionHelper;

public class LogInActivity extends AppCompatActivity {

    private class LogInForm {
        public EditText editTextEmail, editTextPassword;
    }

    public static LogInActivity self;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self = this;

        setContentView(R.layout.activity_login);
        buildLogInView();
    }

    private void buildLogInView() {
        createFormLogIn();
        createTextViewSign();
    }

    private void createFormLogIn() {
        LogInForm logInForm = new LogInForm();
        logInForm.editTextEmail = (EditText) findViewById(R.id.login_edittext_email);
        logInForm.editTextPassword = (EditText) findViewById(R.id.login_edittext_password);

        createButtonConnect(logInForm);
    }

    private void createButtonConnect(final LogInForm logInForm) {
        Button button = (Button) findViewById(R.id.login_button_connect);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!logInForm.editTextEmail.getText().toString().trim().isEmpty()
                        && !logInForm.editTextPassword.getText().toString().trim().isEmpty()) {
                    String email = logInForm.editTextEmail.getText().toString().trim();
                    String password = logInForm.editTextPassword.getText().toString().trim();

                    logIn(email, password);
                }
            }
        });
    }

    private void createTextViewSign() {
        TextView textView = (TextView) findViewById(R.id.login_textview_signup);
        assert textView != null;
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, SignActivity.class));
            }
        });

        GUIHelper.addUnderline(textView);
    }

    private void logIn(String email, String password) {
        LogInHandler.logIn(email, password);
    }

    public void onSuccess(Customer customer) {
        SessionHelper.logIn(this, customer);
    }

    public void onError(String error) {
        OptionDialog.showOkDialog(this, getResources().getString(R.string.login_alertdialog_title_error_login), error);
    }
}