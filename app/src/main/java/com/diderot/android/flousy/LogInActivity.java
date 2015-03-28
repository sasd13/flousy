package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import flousy.util.SessionManager;
import flousy.util.UserManager;
import flousy.util.Validator;
import flousy.util.ValidatorCode;
import flousy.gui.listener.CustomOnTouchListener;
import flousy.gui.widget.CustomDialogBuilder;

public class LogInActivity extends MotherActivity {

    private static int LOADING_TIME_OUT = 2000;
    private Handler handler;
    private Runnable runnable;

    private class ViewHolder {
        public EditText loginEditText, passwordEditText;
        public Button connectButton;
    }

    private ViewHolder form;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        setContentView(R.layout.activity_login_layout);

        //Disable CustomActionBar
        getCustomActionBar().hide();

        this.form = new ViewHolder();

        this.form.loginEditText = (EditText) findViewById(R.id.login_edittext_email);
        this.form.passwordEditText = (EditText) findViewById(R.id.login_edittext_password);

        this.form.connectButton = (Button) findViewById(R.id.login_button_connect);
        this.form.connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (form.loginEditText.getText().toString().trim().length() > 0
                        && form.passwordEditText.getText().toString().trim().length() > 0) {
                    startConnection();
                }
            }
        });
        this.form.connectButton.setOnLongClickListener(new View.OnLongClickListener() {
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
                            Intent intent = new Intent(LogInActivity.this, RestorePasswordActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    });
                    break;
                case R.id.login_textview_signup :
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    });
            }
        }

        //Customize activity default
        customizeColor();
        customizeText();
        customizeDimensions();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(getIntent().hasExtra("CLOSE") && getIntent().getBooleanExtra("CLOSE", false) == true) {
            getIntent().removeExtra("CLOSE");

            if(getIntent().hasExtra("NEW_USER_FIRSTNAME")) {
                Intent intent = new Intent(this, MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("WELCOME", true);
                intent.putExtra("NEW_USER_FIRSTNAME", getIntent().getCharSequenceExtra("NEW_USER_FIRSTNAME"));

                startActivity(intent);
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

    @Override
    public void customizeColor() {
        super.customizeColor();

        if(getContentView() != null) {
            this.form.connectButton.setBackgroundColor(getActivityColor());
            this.form.connectButton.setOnTouchListener(new CustomOnTouchListener(getActivityColor()));
        }
    }

    public void startConnection() {
        UserManager manager = new UserManager(this);
        SessionManager session = (SessionManager) manager.getManager(UserManager.TYPE_SESSION);

        String login = this.form.loginEditText.getEditableText().toString();
        String password = this.form.passwordEditText.getEditableText().toString();

        boolean connected = false;
        ValidatorCode codeEmail = Validator.validEmail(login);
        if(codeEmail == ValidatorCode.OK) {
            connected = session.connect(login, password);
        }

        if(connected == false) {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
            builder.setTitle(R.string.login_alertdialog_login_title_error)
                    .setMessage(R.string.login_alertdialog_login_message_error)
                    .setNeutralButton(null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
            final AlertDialog dialog = builder.create();

            final Intent intent = new Intent(this, MenuActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

            this.runnable = new Runnable() {

                @Override
                public void run() {
                    startActivity(intent);
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