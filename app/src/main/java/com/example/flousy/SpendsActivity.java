package com.example.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import flousy.bean.Category;
import flousy.bean.ListCategories;
import flousy.db.DataAccessor;
import flousy.db.DBManager;
import flousy.gui.widget.recycler.grid.Grid;
import flousy.gui.widget.recycler.grid.GridItem;

public class SpendsActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customRed;

    private DataAccessor dao = DBManager.getDao();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview);

        setColor(getResources().getColor(ACTIVITY_COLOR));

        creadGridCategories();
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

    private void creadGridCategories() {
        Grid grid = new Grid(this);

        RecyclerView gridView = (RecyclerView) findViewById(R.id.recyclerview);
        grid.adapt(gridView);

        ListCategories listCategories = this.dao.selectAllCategories();

        GridItem gridItem;
        Intent intent = new Intent(this, SpendsProductActivity.class);

        for (Category category : listCategories) {
            gridItem = new GridItem();

            gridItem.setColor(getResources().getColor(R.color.transparent));
            gridItem.setText(category.getName());
            gridItem.setIntent(intent);

            grid.addItem(gridItem);
        }
    }
}