package com.diderot.android.flousy;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import flousy.util.ImageBox;


public class MenuActivity extends MyActivity {

    public static final int ACTIVITY_COLOR = DEFAULT_ACTIVITY_COLOR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set activity color before everything
        setActivityColor(ACTIVITY_COLOR);

        //Disable previous remote
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Set LineBar
        setActivityLineBar(R.layout.layout_bar);

        //Set content
        ViewStub stub = (ViewStub) findViewById(R.id.viewStub_layoutContent);
        stub.setLayoutResource(R.layout.layout_tabmenu);

        TableLayout tabLayout = (TableLayout) stub.inflate();
        TableRow tabRow0 = (TableRow) tabLayout.getChildAt(0);

        TableRow tabRow;
        ImageBox imageBox;

        for(int i=0; i<3; i++) {
            tabRow = new TableRow(this);
            tabRow.setLayoutParams(tabRow0.getLayoutParams());
            tabRow.setGravity(Gravity.CENTER);

            imageBox = new ImageBox(this);
            switch(i) {
                case 0:
                    imageBox.setText("Nouveau");
                    imageBox.setBackgroundColor(R.color.customGreen);
                    break;
                case 1:
                    imageBox.setText("Finances");
                    imageBox.setBackgroundColor(R.color.customOrange);
                    break;
                case 2:
                    imageBox.setText("Offres");
                    imageBox.setBackgroundColor(R.color.customYellow);
                    break;
            }
            tabRow.addView(imageBox.getBox());

            imageBox = new ImageBox(this);
            switch(i) {
                case 0:
                    imageBox.setText("Consulter");
                    imageBox.setBackgroundColor(R.color.customRed);
                    break;
                case 1:
                    imageBox.setText("Communauté");
                    imageBox.setBackgroundColor(R.color.customBlue);
                    break;
                case 2:
                    imageBox.setText("Paramètres");
                    imageBox.setBackgroundColor(R.color.customPurple);
                    break;
            }
            tabRow.addView(imageBox.getBox());

            tabLayout.addView(tabRow);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_myactivity, menu);

        MenuItem item = menu.add(R.string.action_search);
        item.setIcon(R.drawable.ic_action_search);

        if (Build.VERSION.SDK_INT >= 11) {
            SearchView searchView = new SearchView(this);
            item.setActionView(new SearchView(this));
        }

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
