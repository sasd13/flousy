package com.example.flousy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import flousy.gui.widget.dialog.CustomDialog;
import flousy.gui.widget.dialog.CustomDialogBuilder;
import flousy.session.Session;

public class UserLogActivity extends Activity {

    private class FormLogInViewHolder {
        public EditText editTextLogin, editTextPassword;
        public Button buttonLogin;
    }

    private static final int LOGIN_TIMEOUT = 2000;

    private FormLogInViewHolder formLogIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_log);

        createFormLogIn();
        customizeView();
    }

    private void createFormLogIn() {
        this.formLogIn = new FormLogInViewHolder();

        this.formLogIn.editTextLogin = (EditText) findViewById(R.id.login_edittext_email);
        this.formLogIn.editTextPassword = (EditText) findViewById(R.id.login_edittext_password);

        this.formLogIn.buttonLogin = (Button) findViewById(R.id.login_button_connect);
        this.formLogIn.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (formLogIn.editTextLogin.getText().toString().trim().length() > 0
                        && formLogIn.editTextPassword.getText().toString().trim().length() > 0) {
                    logIn();
                }
            }
        });
    }

    private void logIn() {
        String login = this.formLogIn.editTextLogin.getEditableText().toString();
        String password = this.formLogIn.editTextPassword.getEditableText().toString();

        if (Session.logIn(login, password)) {
            goToHomeActivity();
        } else {
            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.login_alertdialog_login_title_error),
                    getResources().getString(R.string.login_alertdialog_login_message_error));
        }
    }

    private void goToHomeActivity() {
        CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
        final AlertDialog dialog = builder.create();

        final Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
                dialog.dismiss();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, LOGIN_TIMEOUT);

        dialog.show();
    }

    private void customizeView() {
        //Add underline and link for textViews
        TextView[] textViews = {
                (TextView) findViewById(R.id.login_textview_signup)
        };

        SpannableString text;
        for (TextView textView : textViews) {
            text = new SpannableString(textView.getText().toString());
            text.setSpan(new UnderlineSpan(), 0, text.length(), 0);
            textView.setText(text);

            switch (textView.getId()) {
                case R.id.login_textview_signup :
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(UserLogActivity.this, UserAccountActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            startActivity(intent);
                        }
                    });
            }
        }
    }
}