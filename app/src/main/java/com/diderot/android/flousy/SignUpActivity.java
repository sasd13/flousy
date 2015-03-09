package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;

import flousy.user.SessionManager;
import flousy.user.User;

public class SignUpActivity extends MyActivity {

    private static int SIGNUP_TIME_OUT = 3000;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        setActivityColor(DEFAULT_ACTIVITY_COLOR);

        //Enable ActionBar
        setActionBarEnabled(true);
        setActionBarDisplayHomeAsUpEnabled(true);

        //Set ActivityBar
        setActivityBar(R.layout.layout_activitybarwithtitle);
        setActivityBarTitle("Nouveau compte Flousy");

        //Set ActivityContent
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(R.layout.activity_signup);
        ViewGroup view = (ViewGroup) viewStub.inflate();

        Button signButton = (Button) findViewById(R.id.sign_button);
        signButton.setBackgroundResource(DEFAULT_ACTIVITY_COLOR);
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(new User());
            }
        });
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

    public void signUp(User user) {
        SessionManager session = SessionManager.getInstance(this);
        boolean signed = session.signUp(user);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(signed == false) {
            builder.setTitle(R.string.signup_alertdialog_title_error)
                    .setMessage(R.string.signup_alertdialog_message_error)
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
            intent.putExtra("NEW_USER", true);

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
            this.handler.postDelayed(this.runnable, SIGNUP_TIME_OUT);
        }
    }
}