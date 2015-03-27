package com.diderot.android.flousy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;

import flousy.gui.actionbar.ActionBar;

public class RestorePasswordActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        setContentView(R.layout.activity_restorepassword);

        //Create CustomActionBar
        ActionBar actionBar = new ActionBar();
        ViewStub viewStub = (ViewStub) findViewById(R.id.restorepassword_actionbar_viewstub);
        actionBar.inflate(viewStub);
        actionBar.customize(this);
        actionBar.getTitleView().setText(R.string.activity_restorepassword_name);
        actionBar.setUpEnabled(false);
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
}