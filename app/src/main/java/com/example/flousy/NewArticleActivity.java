package com.example.flousy;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import flousy.constant.Extra;
import flousy.gui.app.KeyboardManager;

public class NewArticleActivity extends MotherActivity {

    private class ViewHolder {
        public EditText editTextName, editTextPrice;
    }

    private ViewHolder formArticle;
    private String  categoryName, articleName;
    private int articleId,categoryId;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_article);

        if(getIntent().hasExtra(Extra.ACTIVITY_COLOR)) {
            int activityColor = getIntent().getIntExtra(Extra.ACTIVITY_COLOR, APP_COLOR);

            //Set ActivityColor immediately after content view
            setColor(activityColor);
        }

        //Set ActivityContent
        this.formArticle = new ViewHolder();

        this.formArticle.editTextName = (EditText) findViewById(R.id.form_article_edittext_name);
        this.formArticle.editTextPrice = (EditText) findViewById(R.id.form_article_edittext_price);

        if (getIntent().hasExtra(Extra.CATEGORY_ID)) {
            categoryId = getIntent().getIntExtra(Extra.CATEGORY_ID, 0);
        }

        if(getIntent().hasExtra(Extra.CATEGORY_NAME)) {
            categoryName = getIntent().getStringExtra(Extra.CATEGORY_NAME);
        }

        if (getIntent().hasExtra(Extra.ARTICLE_ID)) {
            articleId = getIntent().getIntExtra(Extra.ARTICLE_ID, 0);
        }

        if(getIntent().hasExtra(Extra.ARTICLE_NAME)) {
            articleName = getIntent().getStringExtra(Extra.ARTICLE_NAME);

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

        }
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
    
    private void addArticle() {

    }

    private void loadArticle() {

    }

    private void updateArticle() {

    }

    private void deleteArticle() {

    }

    private void shareArticle() {

    }
}