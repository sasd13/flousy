package com.example.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import flousy.bean.Category;
import flousy.bean.ListCategories;
import flousy.db.DBAccessor;
import flousy.db.DBManager;
import flousy.gui.widget.recycler.grid.Grid;
import flousy.gui.widget.recycler.grid.GridItem;

public class SpendsActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customRed;

    private Grid grid;

    private DBAccessor dao = DBManager.getDao();

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
        this.grid.clearItems();

        ListCategories listCategories = (ListCategories) this.dao.selectAll(Category.class.getSimpleName());

        GridItem gridItem;
        Intent intent;
        for(Category category : listCategories) {
            gridItem = new GridItem();

            gridItem.setColor(R.color.transparent);
            gridItem.setText(category.getName());

            intent = new Intent(this, SpendsProductActivity.class);
            gridItem.setIntent(intent);

            this.grid.addItem(gridItem);
        }
    }
}