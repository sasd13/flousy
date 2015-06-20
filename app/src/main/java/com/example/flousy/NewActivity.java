package com.example.flousy;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import flousy.gui.widget.recycler.grid.Grid;

public class NewActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customGreen;

    private Grid grid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //Set ActivityContent
        setContentView(R.layout.recyclerview);

        //Set activity color immediately after content view
        setColor(getResources().getColor(ACTIVITY_COLOR));

        //Set Activity content
        this.grid = new Grid(this);

        RecyclerView gridView = (RecyclerView) findViewById(R.id.recyclerview);
        this.grid.adapt(gridView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}