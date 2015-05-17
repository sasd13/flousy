package com.diderot.android.flousy;

import android.content.Intent;
import android.content.res.Resources;
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

        ImageButton buttonEdit = actionBar.getActionFirstButton();
        buttonEdit.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_edit));
        actionBar.setActionFirstButtonEnabled(true);

        ImageButton buttonAdd = actionBar.getActionSecondButton();
        buttonAdd.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_new));
        actionBar.setActionSecondButtonEnabled(true);

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

        for(int i=0; i<6; i++) {
            gridItem = new GridItem();
            gridItem.setColor(getActivityColor());
            gridItem.setIntent(null);

            switch(i) {
                case 0:
                    gridItem.setText("Nourriture");
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_food));
                    break;
                case 1:
                    gridItem.setText("Soins");
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_drug));
                    break;
                case 2:
                    gridItem.setText("Transports");
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_transport));
                    break;
                case 3:
                    gridItem.setText("Loisirs");
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_controller));
                    break;
                case 4:
                    gridItem.setText("Mode");
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_clothes));
                    break;
                case 5:
                    gridItem.setText("Courses");
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_shopping));
                    break;
            }

            this.gridCategories.addItem(gridItem);
        }
    }
}