package com.diderot.android.flousy;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MyActivity extends ActionBarActivity {

    protected int activity_color;
    protected LinearLayout activity_topLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myactivity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.ic_launcher);

        this.activity_topLine = (LinearLayout) findViewById(R.id.activity_topLine);
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
