package com.example.flousy;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import flousy.gui.widget.recycler.grid.Grid;

public class FinanceActivity extends MotherActivity {

    private Grid grid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview);

        createGridFinances();
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

    private void createGridFinances() {
        this.grid = new Grid(this);

        RecyclerView gridView = (RecyclerView) findViewById(R.id.recyclerview);
        this.grid.adapt(gridView);

        fillGridFinances();
    }

    private void fillGridFinances() {
        this.grid.clearItems();
    }
}