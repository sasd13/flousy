package com.diderot.android.flousy;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import flousy.gui.activitybar.ActivityBar;
import flousy.gui.activitybar.ActivityBarFactory;
import flousy.gui.activitybar.ActivityBarType;
import flousy.gui.color.CustomColor;

public class MyActivity extends ActionBarActivity {

    public static final int APP_COLOR = R.color.customGreenApp;

    private CustomColor activityColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view
        setContentView(R.layout.layout_activity_my);

        //Create activity color with app color
        this.activityColor = new CustomColor(getResources().getColor(APP_COLOR));

        //Enable previous remote
        setActionBarDisplayHomeAsUpEnabled(true);
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
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }

    public void setActionBarEnabled(boolean enabled) {
        if(enabled == true) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                try {
                    getActionBar().show();
                } catch (Exception e) {
                    Log.println(1, null, "ActionBar is null");
                }
            } else {
                getSupportActionBar().show();
            }
        } else {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                try {
                    getActionBar().hide();
                } catch (Exception e) {
                    Log.println(1, null, "ActionBar is null");
                }
            } else {
                getSupportActionBar().hide();
            }
        }
    }

    public void setActionBarDisplayHomeAsUpEnabled(boolean enabled) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            try {
                getActionBar().setDisplayHomeAsUpEnabled(enabled);
            } catch (Exception e) {
                Log.println(1, null, "ActionBar is null");
            }
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
        }
    }

    public CustomColor getActivityColor() {
        return this.activityColor;
    }

    public void setActivityColor(CustomColor activityColor) {
        this.activityColor = activityColor;
    }

    public ActivityBar createActivityBar(ActivityBarType type) {
        ActivityBar activityBar = ActivityBarFactory.create(type);

        if(activityBar != null) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.activitybar_viewstub);
            activityBar.inflate(viewStub);
            activityBar.getView().setBackgroundColor(this.activityColor.getColor());
        }

        return activityBar;
    }

    public View createActivityContent(int layoutResource) {
        ViewStub viewStub = (ViewStub) findViewById(R.id.activitycontent_viewstub);
        viewStub.setLayoutResource(layoutResource);
        View view = viewStub.inflate();

        int i = 0;
        String tag = "color_activity_";
        View viewChild = view.findViewWithTag(tag + i);
        while (viewChild != null) {
            if(viewChild.getClass().getSimpleName().compareTo("TextView") == 0) {
                ((TextView) viewChild).setTextColor(this.activityColor.getColor());
            } else if(viewChild.getClass().getSimpleName().compareTo("Button") == 0) {
                ((Button) viewChild).setTextColor(getResources().getColor(R.color.white));
                viewChild.setBackgroundColor(this.activityColor.getColor());
            } else {
                viewChild.setBackgroundColor(this.activityColor.getColor());
            }

            i++;
            viewChild = view.findViewWithTag(tag + i);
        }

        return view;
    }
}
