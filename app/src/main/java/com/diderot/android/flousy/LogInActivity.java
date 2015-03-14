package com.diderot.android.flousy;

import android.app.AlertDialog;
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
import android.widget.Toast;

import flousy.user.UserManager;
import flousy.util.activitybar.ActivityBarFactory;
import flousy.util.activitybar.BaseActivityBar;
import flousy.util.widget.CustomOnTouchListener;
import flousy.util.widget.CustomDialogBuilder;

public class LogInActivity extends MyActivity {

    private static int LOADING_TIME_OUT = 2000;
    private Handler handler;
    private Runnable runnable;

    private EditText editTextLogin, editTextPassword;
    private Button buttonConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Disable ActionBar
        setActionBarEnabled(false);

        //Set ActivityBar
        BaseActivityBar activityBar = (BaseActivityBar) createActivityBar(ActivityBarFactory.TYPE_BASEACTIVITYBAR);

        //Set ActivityContent
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(R.layout.layout_activity_login);
        View view = viewStub.inflate();

        this.editTextLogin = (EditText) findViewById(R.id.login_edittext_email);
        this.editTextPassword = (EditText) findViewById(R.id.login_edittext_password);

        TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    startConnection();
                    return true;
                }
                return false;
            }
        };

        this.editTextLogin.setOnEditorActionListener(editorActionListener);
        this.editTextPassword.setOnEditorActionListener(editorActionListener);

        this.buttonConnect = (Button) findViewById(R.id.login_button_connect);
        this.buttonConnect.setBackgroundColor(this.getActivityColor().getColor());
        this.buttonConnect.setOnTouchListener(new CustomOnTouchListener(this.getActivityColor()));
        this.buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startConnection();
            }
        });
        this.buttonConnect.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.performClick();
                return false;
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
    protected void onStart() {
        super.onStart();

        if(getIntent().hasExtra("CLOSE") && getIntent().getBooleanExtra("CLOSE", false) == true) {
            getIntent().removeExtra("CLOSE");

            if(getIntent().hasExtra("NEW_USER_FIRSTNAME")) {
                Intent menuActivity = new Intent(this, MenuActivity.class);
                menuActivity.putExtra("WELCOME", true);
                menuActivity.putExtra("NEW_USER_FIRSTNAME", getIntent().getCharSequenceExtra("NEW_USER_FIRSTNAME"));

                startActivity(menuActivity);
            }

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

    public void startConnection() {
        UserManager manager = new UserManager(this);
        boolean connected = false;

        String login = this.editTextLogin.getEditableText().toString();
        String password = this.editTextPassword.getEditableText().toString();

        int errorMessageResource = R.string.login_alertdialog_message_error;
        if(login.length() == 0) {
            errorMessageResource = R.string.login_toast_login_message;
        } else if(password.length() == 0) {
            errorMessageResource = R.string.login_toast_password_message;
        } else {
            connected = manager.connect(login, password);
        }

        if(connected == false) {
            switch (errorMessageResource) {
                case R.string.login_toast_login_message :case R.string.login_toast_password_message :
                    String message = getResources().getString(errorMessageResource);
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                    break;
                case R.string.login_alertdialog_message_error :
                    CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
                    builder.setTitle(R.string.login_alertdialog_title_error)
                            .setMessage(errorMessageResource)
                            .setNeutralButton(null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
            }
        } else {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
            final AlertDialog dialog = builder.create();

            final Intent menuActivity = new Intent(this, MenuActivity.class);
            this.runnable = new Runnable() {

                @Override
                public void run() {
                    startActivity(menuActivity);
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