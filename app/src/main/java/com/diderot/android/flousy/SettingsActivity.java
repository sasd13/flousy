package com.diderot.android.flousy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import flousy.util.DataManager;
import flousy.util.SessionManager;
import flousy.gui.actionbar.ActionBar;
import flousy.gui.app.KeyboardManager;
import flousy.content.user.User;
import flousy.util.UserManager;
import flousy.gui.recycler.drawer.Drawer;
import flousy.gui.widget.CustomDialogBuilder;
import flousy.gui.listener.CustomOnTouchListener;
import flousy.util.Validator;

public class SettingsActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customBrown;

    private static int LOGOUT_TIME_OUT = 2000;
    private Handler handler;
    private Runnable runnable;

    private class ViewHolder {
        public EditText firstNameEditText, lastNameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
        public CheckBox connectCheckBox;
        public Button logoutButton;
    }

    private ViewHolder form;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        setActivityColor(getResources().getColor(ACTIVITY_COLOR));

        //Set ActionBar
        ActionBar actionBar = getCustomActionBar().setNavigationUp(this);
        actionBar.getTitleView().setText(R.string.activity_settings_name);

        //Set Drawer
        Drawer drawer = getDrawer();

        //Set ActivityContent
        setContentView(R.layout.userform_layout);

        this.form = new ViewHolder();

        this.form.firstNameEditText = (EditText) findViewById(R.id.userform_edittext_firstname);
        this.form.lastNameEditText = (EditText) findViewById(R.id.userform_edittext_lastname);
        this.form.emailEditText = (EditText) findViewById(R.id.userform_edittext_email);
        this.form.passwordEditText = (EditText) findViewById(R.id.userform_edittext_password);

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

        this.form.firstNameEditText.setOnEditorActionListener(listener);
        this.form.lastNameEditText.setOnEditorActionListener(listener);
        this.form.emailEditText.setOnEditorActionListener(listener);

        this.form.connectCheckBox = (CheckBox) findViewById(R.id.userform_checkbox);
        TextView validCheckBoxTextView = (TextView) findViewById(R.id.userform_textview_validcheckbox);
        validCheckBoxTextView.setText(R.string.settings_userform_textview_validcheckbox_connect);

        this.form.logoutButton = (Button) findViewById(R.id.userform_button);
        this.form.logoutButton.setText(R.string.settings_userform_button_logout);
        this.form.logoutButton.setOnClickListener(new View.OnClickListener() {
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
        this.form.logoutButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.performClick();
                return false;
            }
        });

        //Customize activity
        customizeColor();
        customizeText();
        customizeDimensions();
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

    @Override
    public void customizeColor() {
        super.customizeColor();

        if(getContentView() != null) {
            this.form.logoutButton.setBackgroundColor(getActivityColor());
            this.form.logoutButton.setOnTouchListener(new CustomOnTouchListener(getActivityColor()));
        }
    }

    public void startInject() {
        UserManager manager = new UserManager(this);
        SessionManager session = (SessionManager) manager.getManager(UserManager.TYPE_SESSION);
        DataManager data = (DataManager) manager.getManager(UserManager.TYPE_DATA);
        User user = data.getUser(session.getUserEmail());
        boolean stayConnect = true;

        this.form.firstNameEditText.setText(user.getFirstName(), TextView.BufferType.EDITABLE);
        this.form.lastNameEditText.setText(user.getLastName(), TextView.BufferType.EDITABLE);
        this.form.emailEditText.setText(user.getEmail(), TextView.BufferType.EDITABLE);
        this.form.passwordEditText.setText("", TextView.BufferType.EDITABLE);

        this.form.connectCheckBox.setChecked(stayConnect);
    }

    public void save() {
        UserManager manager = new UserManager(this);
        DataManager data = (DataManager) manager.getManager(UserManager.TYPE_DATA);
        SessionManager session = (SessionManager) manager.getManager(UserManager.TYPE_SESSION);

        String firstName = this.form.firstNameEditText.getEditableText().toString();
        String lastName = this.form.lastNameEditText.getEditableText().toString();
        String email = this.form.emailEditText.getEditableText().toString();
        String password = "password";
        Boolean connectCheckBox = this.form.connectCheckBox.isChecked();

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

            final Intent intent = new Intent(this, MenuActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);

            this.runnable = new Runnable() {

                @Override
                public void run() {
                    startActivity(intent);
                    dialog.dismiss();
                    finish();
                }
            };

            this.handler = new Handler();
            this.handler.postDelayed(this.runnable, LOGOUT_TIME_OUT);
            dialog.show();
        }
    }
}