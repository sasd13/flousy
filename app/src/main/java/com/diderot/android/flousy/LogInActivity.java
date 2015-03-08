package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        setActivityColor(DEFAULT_ACTIVITY_COLOR);

        //Enable ActionBar
        setActionBarEnabled(false);

        //Set ActivityBar
        setActivityBar(R.layout.layout_activitybar);

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
        }
    }

    public void startConnection(String login, String password) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        User user = SessionManager.connect(settings, login, password);

        if(user == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.alertdialog_title_error)
                    .setMessage(R.string.login_alertdialog_message_error)
                    .setNegativeButton(R.string.alertdialog_button_ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            Intent menuActivity = new Intent(this, MenuActivity.class);
            startActivity(menuActivity);
            finish();
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
}