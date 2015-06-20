package com.diderot.android.flousy;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import flousy.constant.Extra;
import flousy.gui.actionbar.ActionBar;
import flousy.gui.widget.recycler.tab.Tab;

public class ConsultCategoryActivity extends MotherActivity {

    private Tab tab;
    private String  categoryName, articleName;
    private int articleId,categoryId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set ActivityContent
        setContentView(R.layout.recyclerview);

        if(getIntent().hasExtra(Extra.ACTIVITY_COLOR)) {
            int activityColorId = getIntent().getIntExtra(Extra.ACTIVITY_COLOR, APP_COLOR);

            //Set ActivityColor immediately after content view
            setActivityColor(activityColorId);
        }

        //Set CustomActionBar
        ActionBar actionBar = getCustomActionBar();
        actionBar.getTitleView().setText(R.string.activity_spends_name);

        if(getIntent().hasExtra(Extra.CATEGORY_ID)) {
            categoryId = getIntent().getIntExtra(Extra.CATEGORY_ID, 0);
        }

        if(getIntent().hasExtra(Extra.CATEGORY_NAME)) {
            categoryName = getIntent().getStringExtra(Extra.CATEGORY_NAME);

            actionBar.getTitleView().setText(categoryName);
        }

        //Set Activity content
        this.tab = new Tab(this);

        RecyclerView tabView = (RecyclerView) findViewById(R.id.recyclerview);
        this.tab.adapt(tabView);

        initialize();
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

    @Override
    public void initialize() {
        super.initialize();

        addArticlesTabItems();
    }

    public void addArticlesTabItems() {

    }
}