package com.diderot.android.flousy;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

public class MyActivity extends ActionBarActivity {

    public static final int DEFAULT_ACTIVITY_COLOR = R.color.customGreen;

    private int activityColor;
    private View activityBar;

    public void setActionBarEnabled(boolean enabled) {
        if(enabled == true) {
            if(Build.VERSION.SDK_INT >= 11) {
                try {
                    getActionBar().show();
                } catch (Exception e) {
                    Log.println(1, null, "ActionBar is null");
                }
            } else {
                getSupportActionBar().show();
            }
        } else {
            if(Build.VERSION.SDK_INT >= 11) {
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
        if(Build.VERSION.SDK_INT >= 11) {
            try {
                getActionBar().setDisplayHomeAsUpEnabled(enabled);
            } catch (Exception e) {
                Log.println(1, null, "ActionBar is null");
            }
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
        }
    }

    public void setActivityColor (int colorResource) {
        this.activityColor = colorResource;
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

        if(Build.VERSION.SDK_INT >= 11) {
            try {
                getActionBar().setDisplayHomeAsUpEnabled(true);
            } catch (Exception e) {
                Log.println(1, null, "ActionBar is null");
            }
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem item;
        for(int i=0; i<menu.size(); i++) {
            item = menu.getItem(i);
            if (Build.VERSION.SDK_INT >= 11) {
                try {
                    item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                } catch (Exception e) {
                    Log.println(1, null, "ActionBar is null");
                }
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
