package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.flousy.content.handler.LogInHandler;

public class LogInActivity extends AppCompatActivity {

    private class LogInForm {
        public EditText editTextEmail, editTextPassword;
    }

    public static LogInActivity self;
    private LogInHandler logInHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self = this;
        logInHandler = new LogInHandler(this);

        setContentView(R.layout.activity_login);
        buildLogInView();
    }

    private void buildLogInView() {
        LogInForm logInForm = new LogInForm();
        logInForm.editTextEmail = (EditText) findViewById(R.id.login_edittext_email);
        logInForm.editTextPassword = (EditText) findViewById(R.id.login_edittext_password);

        createButtonConnect(logInForm);
        createTextViewSign();
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
        logInHandler.logIn(email, password);
    }
}