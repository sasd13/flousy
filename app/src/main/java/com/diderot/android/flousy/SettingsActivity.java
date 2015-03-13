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

import flousy.user.UserManager;
import flousy.util.activitybar.ActivityBarFactory;
import flousy.util.activitybar.SimpleActivityBar;
import flousy.util.color.CustomColor;
import flousy.util.widget.CustomDialogBuilder;
import flousy.util.widget.CustomOnTouchListener;

public class SettingsActivity extends MyActivity {

    public static final int ACTIVITY_COLOR = R.color.customBrown;

    private static int LOGOUT_TIME_OUT = 2000;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        CustomColor activityColor = new CustomColor(getResources().getColor(ACTIVITY_COLOR));
        setActivityColor(activityColor);

        //Set ActivityBar
        ActivityBarFactory activityBarFactory = new ActivityBarFactory();
        SimpleActivityBar activityBar = (SimpleActivityBar) activityBarFactory.create(ActivityBarFactory.TYPE_SIMPLEACTIVITYBAR);
        setActivityBar(activityBar);

        //Set ActivityContent
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(R.layout.activity_settings);
        ViewGroup view = (ViewGroup) viewStub.inflate();

        Button logoutButton = (Button) findViewById(R.id.logout_button);
        logoutButton.setBackgroundResource(ACTIVITY_COLOR);
        logoutButton.setOnTouchListener(new CustomOnTouchListener(getActivityColor()));
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogBuilder builder = new CustomDialogBuilder(SettingsActivity.this, CustomDialogBuilder.TYPE_TWOBUTTON_YESNO);
                AlertDialog dialog = builder.setTitle(R.string.logout_alertdialog_title)
                        .setMessage(R.string.logout_alertdialog_message)
                        .setPositiveButton(new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                endConnection();
                            }
                        })
                        .setNegativeButton(new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .create();
                dialog.show();
            }
        });
        logoutButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.performClick();
                return false;
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
        UserManager session = UserManager.getInstance();
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
            ViewGroup layoutDialog = (ViewGroup) inflater.inflate(R.layout.layout_customdialog_load, null);

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