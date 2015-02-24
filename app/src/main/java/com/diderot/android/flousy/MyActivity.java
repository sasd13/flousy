package com.diderot.android.flousy;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

public class MyActivity extends ActionBarActivity {

    public static final int DEFAULT_ACTIVITY_COLOR = R.color.customGreen;

    private Object customActionBar;
    private int activityColor;
    private View activityBar;

    public void setCustomActionBarEnabled(boolean enabled) {
        if(enabled == true) {
            /*
            if(Build.VERSION.SDK_INT >= 11) {
                ((android.app.ActionBar) this.customActionBar).show();
            } else {
                ((ActionBar) this.customActionBar).show();
            }
            */

            ((ActionBar) this.customActionBar).show();
        } else {
            /*
            if(Build.VERSION.SDK_INT >= 11) {
                ((android.app.ActionBar) this.customActionBar).hide();
            } else {
                ((ActionBar) this.customActionBar).hide();
            }
            */

            ((ActionBar) this.customActionBar).hide();
        }
    }

    public void setCustomActionBarDisplayHomeAsUpEnabled(boolean enabled) {
        if (this.customActionBar != null) {
            /*
            if(Build.VERSION.SDK_INT >= 11) {
                ((android.app.ActionBar) this.customActionBar).setDisplayHomeAsUpEnabled(enabled);
            } else {
                ((ActionBar) this.customActionBar).setDisplayHomeAsUpEnabled(enabled);
            }*/

            ((ActionBar) this.customActionBar).setDisplayHomeAsUpEnabled(enabled);
        }
    }

    public int getActivityColor() { return this.activityColor; }

    public void setActivityColor (int colorResource) {
        this.activityColor = colorResource;
    }

    public View getActivityBar() {
        return this.activityBar;
    }

    public void setActivityBar(int layoutResource) {
        ViewStub stub = (ViewStub) findViewById(R.id.activitybar_viewstub);
        stub.setLayoutResource(layoutResource);
        this.activityBar = stub.inflate();
        this.activityBar.setBackgroundResource(this.activityColor);
    }

    public void setActivityBarTitle(CharSequence title) {
        TextView textView = (TextView) ((ViewGroup) this.activityBar).getChildAt(0);
        textView.setText(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myactivity);
        /*
        if(Build.VERSION.SDK_INT >= 11) {
            this.customActionBar = (android.app.ActionBar) getActionBar();
            ((android.app.ActionBar) this.customActionBar).setDisplayHomeAsUpEnabled(true);
            ((android.app.ActionBar) this.customActionBar).setIcon(R.drawable.ic_launcher);
        } else {
            this.customActionBar = (ActionBar) getSupportActionBar();
            ((ActionBar) this.customActionBar).setDisplayHomeAsUpEnabled(true);
            ((ActionBar) this.customActionBar).setIcon(R.drawable.ic_launcher);
        }*/

        this.customActionBar = (ActionBar) getSupportActionBar();
        ((ActionBar) this.customActionBar).setDisplayHomeAsUpEnabled(true);
        ((ActionBar) this.customActionBar).setIcon(R.drawable.ic_launcher);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem item;
        for(int i=0; i<menu.size(); i++) {
            item = menu.getItem(i);
            if (Build.VERSION.SDK_INT >= 11) {
                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            }
        }

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
