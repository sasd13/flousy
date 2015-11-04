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

import flousy.constant.Extra;
import flousy.gui.widget.dialog.CustomDialog;
import flousy.gui.widget.dialog.CustomDialogBuilder;
import flousy.session.Session;

public class CustomerLogActivity extends Activity {

    private class ViewHolder {
        public EditText editTextLogin, editTextPassword;
        public Button buttonLogin;
    }

    private static final int LOADING_TIME_OUT = 2000;

    private ViewHolder formUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_customer_log);

        this.formUser = new ViewHolder();

        this.formUser.editTextLogin = (EditText) findViewById(R.id.login_edittext_email);
        this.formUser.editTextPassword = (EditText) findViewById(R.id.login_edittext_password);

        this.formUser.buttonLogin = (Button) findViewById(R.id.login_button_connect);
        this.formUser.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (formUser.editTextLogin.getText().toString().trim().length() > 0
                        && formUser.editTextPassword.getText().toString().trim().length() > 0) {
                    login();
                }
            }
        });

        //Add underline and link for textViews
        TextView[] textViews = {
                (TextView) findViewById(R.id.login_textview_signup)
        };

        SpannableString text;
        for(TextView textView : textViews) {
            text = new SpannableString(textView.getText().toString());
            text.setSpan(new UnderlineSpan(), 0, text.length(), 0);
            textView.setText(text);

            switch (textView.getId()) {
                case R.id.login_textview_signup :
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(CustomerLogActivity.this, CustomerAccountActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    });
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent().hasExtra(Extra.CLOSE) && getIntent().getBooleanExtra(Extra.CLOSE, false)) {
            getIntent().removeExtra(Extra.CLOSE);

            if (getIntent().hasExtra(Extra.CUSTOMER_FIRSTNAME)) {
                Intent intent = new Intent(this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Extra.WELCOME, true);
                intent.putExtra(Extra.CUSTOMER_FIRSTNAME, getIntent().getStringExtra(Extra.CATEGORY_ID));

                startActivity(intent);
            }

            finish();
        }
    }

    private void login() {
        String login = this.formUser.editTextLogin.getEditableText().toString();
        String password = this.formUser.editTextPassword.getEditableText().toString();

        if (Session.logIn(login, password)) {
            goToMenuActivity();
        } else {
            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.login_alertdialog_login_title_error),
                    getResources().getString(R.string.login_alertdialog_login_message_error));
        }
    }

    private void goToMenuActivity() {
        CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
        final AlertDialog dialog = builder.create();

        final Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
                dialog.dismiss();
                finish();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, LOADING_TIME_OUT);

        dialog.show();
    }
}