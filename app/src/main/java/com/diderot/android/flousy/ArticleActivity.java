package com.diderot.android.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import flousy.gui.actionbar.ActionBar;
import flousy.gui.activitybar.ActivityBarType;
import flousy.gui.activitybar.TitledActivityBar;

public class ArticleActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customBlue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.form_article_layout);

        if(getIntent().hasExtra("ACTIVITY_COLOR")) {
            int activityColorId = getIntent().getIntExtra("ACTIVITY_COLOR", getResources().getColor(R.color.customGreen));
            getIntent().removeExtra("ACTIVITY_COLOR");

            //Set ActivityColor immediately after content view
            setActivityColor(activityColorId);
        }

        //Set CustomActionBar
        ActionBar actionBar = getCustomActionBar();
        actionBar.getTitleView().setText(getResources().getString(R.string.activity_article_name));

        actionBar.setSubTitleViewEnabled(true);

        if(getIntent().hasExtra("CATEGORY_NAME")) {
            String categoryName = getIntent().getStringExtra("CATEGORY_NAME");
            getIntent().removeExtra("CATEGORY_NAME_ID");

            actionBar.getSubTitleView().setText(categoryName);
        }

        ImageButton buttonDiscard = actionBar.getActionFirstButton();
        buttonDiscard.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_discard));
        actionBar.setActionFirstButtonEnabled(true);

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
}