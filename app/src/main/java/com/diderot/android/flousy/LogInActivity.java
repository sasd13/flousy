package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import flousy.user.SessionManager;
import flousy.user.User;

public class LogInActivity extends MyActivity {

    private static int LOADING_TIME_OUT = 2000;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        setActivityColor(DEFAULT_ACTIVITY_COLOR);

        //Enable ActionBar
        setActionBarEnabled(false);

        //Set ActivityContent
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(R.layout.activity_login);
        ViewGroup view = (ViewGroup) viewStub.inflate();

        final EditText editTextLogin = (EditText) findViewById(R.id.login_edittext_email);
        final EditText editTextPassword = (EditText) findViewById(R.id.login_edittext_password);

        editTextLogin.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO && editTextPassword.getText().length() > 0) {

                    startConnection(editTextLogin.getText().toString(), editTextPassword.getText().toString());
                    return true;
                }
                return false;
            }
        });

        editTextPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO && editTextLogin.getText().length() > 0) {
                    startConnection(editTextLogin.getText().toString(), editTextPassword.getText().toString());
                    return true;
                }
                return false;
            }
        });

        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setBackgroundResource(DEFAULT_ACTIVITY_COLOR);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextLogin.getText().length() > 0 && editTextPassword.getText().length() > 0) {
                    startConnection(editTextLogin.getText().toString(), editTextPassword.getText().toString());
                }
            }
        });

        //Add underline for textViews
        TextView[] textViews = {
                (TextView) findViewById(R.id.login_textview_restorepassword),
                (TextView) findViewById(R.id.login_textview_signup)
        };

        TextView textView = null;
        SpannableString text;

        for(int i=0; i<textViews.length; i++) {
            textView = textViews[i];
            text = new SpannableString(textView.getText().toString());
            text.setSpan(new UnderlineSpan(), 0, text.length(), 0);
            textView.setText(text);

            switch (textView.getId()) {
                case R.id.login_textview_restorepassword :
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent restorePasswordActivity = new Intent(LogInActivity.this, RestorePasswordActivity.class);
                            startActivity(restorePasswordActivity);
                        }
                    });
                    break;
                case R.id.login_textview_signup :
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent signUpActivity = new Intent(LogInActivity.this, SignUpActivity.class);
                            startActivity(signUpActivity);
                        }
                    });
            }
        }
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

    public void startConnection(String login, String password) {
        SessionManager session = SessionManager.getInstance(this);
        User user = session.connect(login, password);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(user == null) {
            builder.setTitle(R.string.login_alertdialog_title_error)
                    .setMessage(R.string.login_alertdialog_message_error)
                    .setNeutralButton(R.string.alertdialog_button_ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            LayoutInflater inflater = getLayoutInflater();

            builder.setView(inflater.inflate(R.layout.layout_customdialog, null));

            final AlertDialog dialog = builder.create();
            final Intent intent = new Intent(this, MenuActivity.class);

            this.runnable = new Runnable() {

                @Override
                public void run() {
                    startActivity(intent);
                    dialog.dismiss();
                    finish();
                }
            };

            this.handler = new Handler();
            dialog.show();
            this.handler.postDelayed(this.runnable, LOADING_TIME_OUT);
        }
    }
}