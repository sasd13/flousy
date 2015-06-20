package com.diderot.android.flousy;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import flousy.gui.actionbar.ActionBar;
import flousy.gui.app.KeyboardManager;

public class SettingsActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customBrown;

    private class ViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail;
    }

    private ViewHolder formUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set ActivityContent
        setContentView(R.layout.form_user_layout);

        //Set activity color immediately after content view
        setActivityColor(getResources().getColor(ACTIVITY_COLOR));

        //Set ActionBar
        ActionBar actionBar = getCustomActionBar();
        actionBar.getTitleView().setText(R.string.activity_settings_name);

        //Set ActivityContent
        this.formUser = new ViewHolder();

        this.formUser.editTextFirstName = (EditText) findViewById(R.id.form_user_edittext_firstname);
        this.formUser.editTextLastName = (EditText) findViewById(R.id.form_user_edittext_lastname);
        this.formUser.editTextEmail = (EditText) findViewById(R.id.form_user_edittext_email);

        TextView.OnEditorActionListener listener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    KeyboardManager.hide(v);
                    updateUser();
                    return true;
                }
                return false;
            }
        };

        this.formUser.editTextFirstName.setOnEditorActionListener(listener);
        this.formUser.editTextLastName.setOnEditorActionListener(listener);
        this.formUser.editTextEmail.setOnEditorActionListener(listener);

        initialize();
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadUser();
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

    public void loadUser() {

    }

    public void updateUser() {

    }
}