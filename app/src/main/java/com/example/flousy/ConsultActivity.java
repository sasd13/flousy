package com.example.flousy;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import flousy.gui.widget.recycler.grid.Grid;

public class ConsultActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customRed;

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

        //Add items
        addCategoriesGridItems();
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

    private void addCategoriesGridItems() {

    }
}