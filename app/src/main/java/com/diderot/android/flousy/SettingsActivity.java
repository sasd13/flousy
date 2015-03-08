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
import android.widget.TextView;

import flousy.user.SessionManager;

public class SettingsActivity extends MyActivity {

    public static final int ACTIVITY_COLOR = R.color.customBrown;
    private static int LOGOUT_TIME_OUT = 2000;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        setActivityColor(ACTIVITY_COLOR);

        //Set ActivityBar
        setActivityBar(R.layout.layout_activitybar);

        //Set ActivityContent
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(R.layout.activity_settings);
        ViewGroup view = (ViewGroup) viewStub.inflate();

        Button logoutButton = (Button) findViewById(R.id.logout_button);
        logoutButton.setBackgroundResource(ACTIVITY_COLOR);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle(R.string.logout_alertdialog_title)
                        .setMessage(R.string.logout_alertdialog_message)
                        .setPositiveButton(R.string.alertdialog_button_yes, new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 endConnection();
                             }
                        })
                        .setNegativeButton(R.string.alertdialog_button_no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
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

    public void endConnection() {
        SessionManager session = SessionManager.getInstance(this);
        boolean deconnected = session.deconnect();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(deconnected == false) {
            builder.setTitle(R.string.alertdialog_title_error)
                    .setMessage(R.string.logout_alertdialog_message_error)
                    .setNeutralButton(R.string.alertdialog_button_ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            LayoutInflater inflater = getLayoutInflater();
            ViewGroup layoutDialog = (ViewGroup) inflater.inflate(R.layout.layout_customdialog, null);
            TextView dialogMessage = (TextView) layoutDialog.getChildAt(1);
            dialogMessage.setText(R.string.customdialog_message_logout);

            builder.setView(layoutDialog);

            final AlertDialog dialog = builder.create();
            final Intent menuActivity = new Intent(this, MenuActivity.class);
            menuActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            menuActivity.putExtra("EXIT", true);

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
            this.handler.postDelayed(this.runnable, LOGOUT_TIME_OUT);
        }
    }
}