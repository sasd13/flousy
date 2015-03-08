package com.diderot.android.flousy;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

public class MyActivity extends ActionBarActivity {

    public static final String PREFS_NAME = "PrefsFile";
    public static final int DEFAULT_ACTIVITY_COLOR = R.color.customGreen;
    private int activityColor;

    public final void setActivityColor (int colorResource) {
        this.activityColor = colorResource;
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

    public void setActivityBar(int layoutResource) {
        ViewStub stub = (ViewStub) findViewById(R.id.activitybar_viewstub);
        stub.setLayoutResource(layoutResource);
        View activityBar = stub.inflate();
        activityBar.setBackgroundResource(this.activityColor);
    }

    public void setActivityBarTitle(CharSequence title) {
        TextView textView = (TextView) findViewById(R.id.activitybarwithtitle_textview);
        textView.setText(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my);

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
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
