package com.sasd13.flousy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.flousy.session.Session;

public class LogActivity extends Activity {

    private class FormLogViewHolder {
        public EditText editTextEmail, editTextPassword;
        public Button buttonConnect;
    }

    private static final int LOGIN_TIMEOUT = 2000;

    private FormLogViewHolder formLog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log);

        createFormLog();
        customizeView();
    }

    private void createFormLog() {
        this.formLog = new FormLogViewHolder();

        this.formLog.editTextEmail = (EditText) findViewById(R.id.log_edittext_email);
        this.formLog.editTextPassword = (EditText) findViewById(R.id.log_edittext_password);

        this.formLog.buttonConnect = (Button) findViewById(R.id.log_button_connect);
        this.formLog.buttonConnect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (formLog.editTextEmail.getText().toString().trim().length() > 0
                        && formLog.editTextPassword.getText().toString().trim().length() > 0) {
                    logIn();
                }
            }
        });
    }

    private void logIn() {
        String email = this.formLog.editTextEmail.getText().toString().trim();
        String password = this.formLog.editTextPassword.getText().toString().trim();

        if (Session.logIn(email, password)) {
            goToHomeActivity();
        } else {
            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.log_alertdialog_title_error_log),
                    getResources().getString(R.string.log_alertdialog_message_error_log));
        }
    }

    private void goToHomeActivity() {
        final WaitDialog waitDialog = new WaitDialog(this);

        final Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
                waitDialog.dismiss();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, LOGIN_TIMEOUT);

        waitDialog.show();
    }

    private void customizeView() {
        //Add underline and link for textViews
        TextView[] textViews = {
                (TextView) findViewById(R.id.log_textview_signup)
        };

        SpannableString text;
        for (TextView textView : textViews) {
            text = new SpannableString(textView.getText().toString());
            text.setSpan(new UnderlineSpan(), 0, text.length(), 0);
            textView.setText(text);

            switch (textView.getId()) {
                case R.id.log_textview_signup :
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(LogActivity.this, SignActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            startActivity(intent);
                        }
                    });
            }
        }
    }
}