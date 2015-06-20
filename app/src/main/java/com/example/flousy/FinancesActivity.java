package com.example.flousy;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import flousy.gui.widget.recycler.grid.Grid;

public class FinancesActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customOrange;

    private Grid grid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview);

        //Set ActivityColor immediately after content view
        setColor(getResources().getColor(ACTIVITY_COLOR));

        //Set ActivityContent
        this.grid = new Grid(this);

        RecyclerView gridView = (RecyclerView) findViewById(R.id.recyclerview);
        this.grid.adapt(gridView);


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