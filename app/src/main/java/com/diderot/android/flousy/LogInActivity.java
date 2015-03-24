package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import flousy.data.SessionManager;
import flousy.data.UserManager;
import flousy.gui.activitycontent.ActivityContentCustomizer;
import flousy.gui.listener.CustomOnTouchListener;
import flousy.gui.widget.CustomDialogBuilder;
import flousy.gui.app.KeyboardManager;
import flousy.gui.app.NativeActionBarManager;

public class LogInActivity extends ActionBarActivity {

    private static int LOADING_TIME_OUT = 2000;
    private Handler handler;
    private Runnable runnable;

    private EditText editTextLogin, editTextPassword;
    private Button buttonConnect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        setContentView(R.layout.activity_login);

        //Disable native ActionBar
        NativeActionBarManager.setActionBarEnabled(this, false);

        //Set ActivityContent
        View view = findViewById(R.id.login_activitycontent);
        ActivityContentCustomizer.customize(this, view);

        this.editTextLogin = (EditText) findViewById(R.id.login_edittext_email);
        this.editTextPassword = (EditText) findViewById(R.id.login_edittext_password);

        this.buttonConnect = (Button) findViewById(R.id.login_button_connect);
        this.buttonConnect.setOnTouchListener(new CustomOnTouchListener(getResources().getColor(MotherActivity.APP_COLOR)));
        this.buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextLogin.getEditableText().toString().isEmpty() == false
                        && editTextPassword.getEditableText().toString().isEmpty() == false)
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

        //Add underline and link for textViews
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
        return super.onOptionsItemSelected(item);
    }

    public void startConnection() {
        UserManager manager = new UserManager(this);
        SessionManager session = (SessionManager) manager.getManager(UserManager.TYPE_SESSION);

        String login = null;
        try {
            login = this.editTextLogin.getEditableText().toString();
        } catch (Exception e) {
            Log.e("error_login", e.getMessage());
        }
        String password = this.editTextPassword.getEditableText().toString();

        boolean connected = false;
        int messageResource = R.string.login_alertdialog_login_message_error;
        if(login.length() != 0 && password.length() != 0) {
            connected = session.connect(login, password);
        }

        if(connected == false) {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
            builder.setTitle(R.string.login_alertdialog_login_title_error)
                    .setMessage(messageResource)
                    .setNeutralButton(null);
            AlertDialog dialog = builder.create();
            dialog.show();
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
            this.handler.postDelayed(this.runnable, LOADING_TIME_OUT);
            dialog.show();
        }
    }
}