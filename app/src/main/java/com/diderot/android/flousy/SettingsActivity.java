package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;

import flousy.user.SessionManager;
import flousy.user.User;

public class SettingsActivity extends MyActivity {

    public static final int ACTIVITY_COLOR = R.color.customBrown;

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

        Button loginButton = (Button) findViewById(R.id.logout_button);
        loginButton.setBackgroundResource(ACTIVITY_COLOR);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle(R.string.logout_alertdialog_title)
                        .setMessage(R.string.logout_alertdialog_message)
                        .setNegativeButton(R.string.alertdialog_button_no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        })
                        .setPositiveButton(R.string.alertdialog_button_yes, new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 endConnection();
                             }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void endConnection() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean deconnected = SessionManager.deconnect(settings);

        if(deconnected == false) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.alertdialog_title_error)
                    .setMessage(R.string.logout_alertdialog_message_error)
                    .setNegativeButton(R.string.alertdialog_button_ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        } else {

            Intent menuActivity = new Intent(this, MenuActivity.class);
            menuActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            menuActivity.putExtra("EXIT", true);
            startActivity(menuActivity);
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