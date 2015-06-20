package com.diderot.android.flousy;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import flousy.constant.Extra;
import flousy.gui.actionbar.ActionBar;
import flousy.gui.app.KeyboardManager;

public class ArticleActivity extends MotherActivity {

    private class ViewHolder {
        public EditText editTextName, editTextPrice;
    }

    private ViewHolder formArticle;
    private String  categoryName, articleName;
    private int articleId,categoryId;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.form_article_layout);

        if(getIntent().hasExtra(Extra.ACTIVITY_COLOR)) {
            int activityColor = getIntent().getIntExtra(Extra.ACTIVITY_COLOR, APP_COLOR);

            //Set ActivityColor immediately after content view
            setActivityColor(activityColor);
        }

        //Set CustomActionBar
        ActionBar actionBar = getCustomActionBar();
        actionBar.getTitleView().setText(getResources().getString(R.string.activity_article_name));

        //Set ActivityContent
        this.formArticle = new ViewHolder();

        this.formArticle.editTextName = (EditText) findViewById(R.id.form_article_edittext_name);
        this.formArticle.editTextPrice = (EditText) findViewById(R.id.form_article_edittext_price);

        if (getIntent().hasExtra(Extra.CATEGORY_ID)) {
            categoryId = getIntent().getIntExtra(Extra.CATEGORY_ID, 0);
        }

        if(getIntent().hasExtra(Extra.CATEGORY_NAME)) {
            categoryName = getIntent().getStringExtra(Extra.CATEGORY_NAME);

            actionBar.getTitleView().setText(categoryName);
        }

        if (getIntent().hasExtra(Extra.ARTICLE_ID)) {
            articleId = getIntent().getIntExtra(Extra.ARTICLE_ID, 0);
        }

        if(getIntent().hasExtra(Extra.ARTICLE_NAME)) {
            articleName = getIntent().getStringExtra(Extra.ARTICLE_NAME);

            actionBar.getSubTitleView().setText(articleName);
            actionBar.setSubTitleViewEnabled(true);

            ImageButton buttonShare = actionBar.getActionFirstButton();
            buttonShare.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_share));
            actionBar.setActionFirstButtonEnabled(true);

            buttonShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shareArticle();
                }
            });

            ImageButton buttonDiscard = actionBar.getActionSecondButton();
            buttonDiscard.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_discard));
            actionBar.setActionSecondButtonEnabled(true);

            buttonDiscard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteArticle();
                }
            });

            TextView.OnEditorActionListener listener = new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(actionId == EditorInfo.IME_ACTION_DONE) {
                        KeyboardManager.hide(v);
                        updateArticle();
                        return true;
                    }
                    return false;
                }
            };

            this.formArticle.editTextName.setOnEditorActionListener(listener);
            this.formArticle.editTextPrice.setOnEditorActionListener(listener);

            loadArticle();
        } else {
            actionBar.getSubTitleView().setText(getResources().getString(R.string.activity_new_name));
            actionBar.setSubTitleViewEnabled(true);

            ImageButton buttonValid = actionBar.getActionFirstButton();
            buttonValid.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_accept));
            actionBar.setActionFirstButtonEnabled(true);

            buttonValid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (formArticle.editTextName.getText().toString().trim().length() > 0
                            && formArticle.editTextPrice.getText().toString().trim().length() > 0) {
                        addArticle();
                    }
                }
            });
        }

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
    
    public void addArticle() {

    }

    public void loadArticle() {

    }

    public void updateArticle() {

    }

    public void deleteArticle() {

    }

    public void shareArticle() {

    }
}