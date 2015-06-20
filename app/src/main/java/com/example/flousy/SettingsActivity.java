package com.example.flousy;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

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
        setContentView(R.layout.activity_settings);

        //Set activity color immediately after content view
        setColor(getResources().getColor(ACTIVITY_COLOR));

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
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadUser() {

    }

    private void updateUser() {

    }
}