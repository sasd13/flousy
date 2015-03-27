package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import flousy.gui.activitycontent.ActivityContentCustomizer;
import flousy.util.DataManager;
import flousy.util.SessionManager;
import flousy.gui.actionbar.ActionBar;
import flousy.gui.app.KeyboardManager;
import flousy.content.user.User;
import flousy.util.UserManager;
import flousy.gui.drawer.Drawer;
import flousy.gui.widget.CustomDialogBuilder;
import flousy.gui.listener.CustomOnTouchListener;
import flousy.util.Validator;

public class SettingsActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customBrown;

    private static int LOGOUT_TIME_OUT = 2000;
    private Handler handler;
    private Runnable runnable;

    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;
    private CheckBox checkBoxConnect;
    private Button logoutButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        setActivityColor(getResources().getColor(ACTIVITY_COLOR));

        //Set ActionBar
        ActionBar actionBar = getCustomActionBar().customize(this);
        actionBar.getTitleView().setText(R.string.activity_settings_name);

        //Set Drawer
        Drawer drawer = getDrawer();

        //Set ActivityContent
        View view = createActivityContent(R.layout.activity_layout_settings);
        ActivityContentCustomizer.customize(view, this);

        TextView.OnEditorActionListener listener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    KeyboardManager.hide(v);
                    save();
                    return true;
                }
                return false;
            }
        };

        this.editTextFirstName = (EditText) findViewById(R.id.settings_edittext_firstname);
        this.editTextLastName = (EditText) findViewById(R.id.settings_edittext_lastname);
        this.editTextEmail = (EditText) findViewById(R.id.settings_edittext_email);
        this.editTextPassword = (EditText) findViewById(R.id.settings_edittext_password);

        this.editTextFirstName.setOnEditorActionListener(listener);
        this.editTextLastName.setOnEditorActionListener(listener);
        this.editTextEmail.setOnEditorActionListener(listener);

        this.checkBoxConnect = (CheckBox) findViewById(R.id.settings_checkbox_connect);

        this.logoutButton = (Button) findViewById(R.id.settings_button_logout);
        this.logoutButton.setBackgroundColor(getActivityColor());
        this.logoutButton.setOnTouchListener(new CustomOnTouchListener(getActivityColor()));
        this.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogBuilder builder = new CustomDialogBuilder(SettingsActivity.this, CustomDialogBuilder.TYPE_TWOBUTTON_YESNO);
                builder.setTitle(R.string.settings_alertdialog_logout_title)
                        .setMessage(R.string.alertdialog_confirm_message)
                        .setPositiveButton(new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                endConnection();
                            }
                        })
                        .setNegativeButton(null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        this.logoutButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.performClick();
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        startInject();
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

    public void startInject() {
        UserManager manager = new UserManager(this);
        SessionManager session = (SessionManager) manager.getManager(UserManager.TYPE_SESSION);
        DataManager data = (DataManager) manager.getManager(UserManager.TYPE_DATA);
        User user = data.getUser(session.getUserEmail());
        boolean stayConnect = true;

        this.editTextFirstName.setText(user.getFirstName(), TextView.BufferType.EDITABLE);
        this.editTextLastName.setText(user.getLastName(), TextView.BufferType.EDITABLE);
        this.editTextEmail.setText(user.getEmail(), TextView.BufferType.EDITABLE);
        this.editTextPassword.setText("", TextView.BufferType.EDITABLE);

        this.checkBoxConnect.setChecked(stayConnect);
    }

    public void save() {
        UserManager manager = new UserManager(this);
        DataManager data = (DataManager) manager.getManager(UserManager.TYPE_DATA);
        SessionManager session = (SessionManager) manager.getManager(UserManager.TYPE_SESSION);

        String firstName = this.editTextFirstName.getEditableText().toString();
        String lastName = this.editTextLastName.getEditableText().toString();
        String email = null;
        try {
            email = this.editTextEmail.getEditableText().toString();
        } catch (Exception e) {
            Log.e("error_email", e.getMessage());
        }
        String password = "password";

        String phoneNumber = "0000";
        Drawable image = null;

        User user = new User(firstName, lastName, phoneNumber, email, password, image);

        boolean valid = Validator.validUser(user);
        if(valid == true) {
            boolean updated = data.setUser(session.getUserEmail(), user);
            if(updated == true) {
                session.updateSession(user.getEmail());
            }
        }
    }

    public void endConnection() {
        UserManager manager = new UserManager(this);
        SessionManager session = (SessionManager) manager.getManager(UserManager.TYPE_SESSION);

        boolean deconnected = session.deconnect();
        if(deconnected == false) {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
            builder.setTitle(R.string.alertdialog_title_error)
                    .setMessage(R.string.settings_alertdialog_lougout_message_error)
                    .setNeutralButton(null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
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