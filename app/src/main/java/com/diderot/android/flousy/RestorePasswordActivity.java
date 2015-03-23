package com.diderot.android.flousy;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;

import flousy.gui.actionbar.ActionBarCustomizer;
import flousy.gui.actionbar.ActionBarFactory;
import flousy.gui.actionbar.ActionBarType;
import flousy.gui.actionbar.BaseActionBar;

public class RestorePasswordActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseActionBar actionBar = (BaseActionBar) ActionBarFactory.create(this, ActionBarType.BASEBAR);
        if(actionBar != null) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.restorepassword_actionbar_viewstub);
            actionBar.inflate(viewStub);

            ActionBarCustomizer.customize(actionBar, this);

            actionBar.setPreviousEnabled(true);
            actionBar.setDrawerEnabled(false);
            actionBar.getTextViewTitle().setText(R.string.activity_restorepassword_name);
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
}