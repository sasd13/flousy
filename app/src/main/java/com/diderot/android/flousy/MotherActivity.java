package com.diderot.android.flousy;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;

import flousy.gui.actionbar.AbstractActionBar;
import flousy.gui.actionbar.ActionBarCustomizer;
import flousy.gui.actionbar.ActionBarFactory;
import flousy.gui.actionbar.ActionBarType;
import flousy.gui.activitybar.ActivityBar;
import flousy.gui.activitybar.ActivityBarFactory;
import flousy.gui.activitybar.ActivityBarType;
import flousy.gui.activitycontent.ActivityContentCustomizer;
import flousy.gui.color.CustomColor;
import flousy.gui.app.NativeActionBarManager;

public class MotherActivity extends ActionBarActivity {

    public static final int APP_COLOR = R.color.customGreenApp;

    private int activityColor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        setContentView(R.layout.activity_mother);

        //Disable native
        NativeActionBarManager.setActionBarEnabled(this, false);

        //Create activity color with app color
        this.activityColor = getResources().getColor(APP_COLOR);
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

        //Disable re-onCreate for subclasses on up button click
        return super.onOptionsItemSelected(item);
    }

    public int getActivityColor() {
        return this.activityColor;
    }

    public void setActivityColor(int activityColor) {
        this.activityColor = activityColor;
    }

    public AbstractActionBar createActionBar(ActionBarType type) {
        AbstractActionBar actionBar = ActionBarFactory.create(this, type);

        if(actionBar != null) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.actionbar_viewstub);
            actionBar.inflate(viewStub);
            ActionBarCustomizer.customize(actionBar, this);
        }

        return actionBar;
    }

    public ActivityBar createActivityBar(ActivityBarType type) {
        ActivityBar activityBar = ActivityBarFactory.create(type);

        if(activityBar != null) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.activitybar_viewstub);
            activityBar.inflate(viewStub);
            activityBar.getView().setBackgroundColor(CustomColor.colorDarker(this.activityColor));
        }

        return activityBar;
    }

    public View createActivityContent(int layoutResource) {
        View view = null;

        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(layoutResource);
        view = viewStub.inflate();
        ActivityContentCustomizer.customizeColor(view, this);

        return view;
    }
}
