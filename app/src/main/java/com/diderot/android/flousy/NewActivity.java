package com.diderot.android.flousy;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;


import flousy.gui.actionbar.ActionBar;
import flousy.gui.color.ColorBrightness;
import flousy.gui.recycler.drawer.Drawer;
import flousy.gui.recycler.grid.Grid;
import flousy.gui.recycler.grid.GridItem;

public class NewActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customGreen;

    private Grid gridCategories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set ActivityContent
        setContentView(R.layout.grid);

        //Set activity color immediately after content view
        setActivityColor(getResources().getColor(ACTIVITY_COLOR));

        //Set CustomActionBar
        ActionBar actionBar = getCustomActionBar();
        actionBar.getTitleView().setText(R.string.activity_new_name);

        TextView subTitle = actionBar.getSubTitleView();
        subTitle.setText(R.string.new_actionbar_textview_subtitle);
        actionBar.setSubTitleViewEnabled(true);

        //Set Activity content
        RecyclerView gridView = (RecyclerView) findViewById(R.id.grid_view);
        this.gridCategories = new Grid(this);
        this.gridCategories.adapt(gridView);

        //Add items
        addCategoriesGridItems();

        //Customize activity
        customizeColor();
        customizeText();
        customizeDimensions();
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

    public void addCategoriesGridItems() {
        GridItem gridItem;
        Resources resources = getResources();

        String nameCategory;
        Drawable image;
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra("ACTIVITY_COLOR", getActivityColor());

        for(int i=0; i<8; i++) {
            gridItem = new GridItem();
            gridItem.setColor(getActivityColor());

            switch(i) {
                case 0:
                    nameCategory = "Nourriture";
                    image = resources.getDrawable(R.drawable.griditem_food);
                    break;
                case 1:
                    nameCategory = "Soins";
                    image = resources.getDrawable(R.drawable.griditem_drug);
                    break;
                case 2:
                    nameCategory = "Transports";
                    image = resources.getDrawable(R.drawable.griditem_transport);
                    break;
                case 3:
                    nameCategory = "Loisirs";
                    image = resources.getDrawable(R.drawable.griditem_controller);
                    break;
                case 4:
                    nameCategory = "Mode";
                    image = resources.getDrawable(R.drawable.griditem_clothes);
                    break;
                case 5:
                    nameCategory = "Courses";
                    image = resources.getDrawable(R.drawable.griditem_shopping);
                    break;
                case 6:
                    nameCategory = "Maison";
                    image = resources.getDrawable(R.drawable.griditem_home);
                    break;
                default:
                    nameCategory = "Autres";
                    image = resources.getDrawable(R.drawable.griditem);
                    break;
            }

            gridItem.setText(nameCategory);
            gridItem.setImage(image);
            gridItem.setColor(getActivityColor());

            intent.putExtra("CATEGORY_NAME", nameCategory);
            gridItem.setIntent(intent);

            this.gridCategories.addItem(gridItem);
        }
    }
}