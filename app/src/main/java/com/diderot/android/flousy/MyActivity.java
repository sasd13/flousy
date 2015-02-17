package com.diderot.android.flousy;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;


public class MyActivity extends ActionBarActivity {

    private int activityColor = R.color.customGreen;
    private View activityLineBar;

    public int getActivityColor() {
        return this.activityColor;
    }

    public void setActivityColor(int color) {
        this.activityColor = color;
    }

    public View getActivityLineBar() {
        return this.activityLineBar;
    }

    public void setActivityLineBar(int layoutResource) {
        ViewStub stub = (ViewStub) findViewById(R.id.viewStub_layoutBar);
        stub.setLayoutResource(layoutResource);
        this.activityLineBar = stub.inflate();
        this.activityLineBar.setBackgroundResource(this.activityColor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myactivity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.ic_launcher);
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
