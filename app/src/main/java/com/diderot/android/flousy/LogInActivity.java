package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import flousy.user.UserManager;
import flousy.user.User;
import flousy.util.activitybar.ActivityBarFactory;
import flousy.util.activitybar.SimpleActivityBar;
import flousy.util.color.CustomColor;
import flousy.util.widget.CustomOnTouchListener;
import flousy.util.widget.CustomDialogBuilder;

public class LogInActivity extends MyActivity {

    private static int LOADING_TIME_OUT = 2000;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Disable ActionBar
        setActionBarEnabled(false);

        //Set ActivityBar
        ActivityBarFactory factory = new ActivityBarFactory();
        SimpleActivityBar activityBar = (SimpleActivityBar) factory.createActivityBar(ActivityBarFactory.TYPE_SIMPLEACTIVITYBAR);
        setActivityBar(activityBar);

        //Set ActivityContent
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(R.layout.activity_login);
        View view = viewStub.inflate();

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
        loginButton.setBackgroundColor(getActivityColor().getColor());
        loginButton.setOnTouchListener(new CustomOnTouchListener(getActivityColor()));
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

        SpannableString text;
        for(TextView textView : textViews) {
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
        UserManager session = UserManager.getInstance();
        User user = session.connect(login, password);

        CustomDialogBuilder builder;

        if(user == null) {
            builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
            builder.setTitle(R.string.login_alertdialog_title_error)
                .setMessage(R.string.login_alertdialog_message_error)
                .setNeutralButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
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