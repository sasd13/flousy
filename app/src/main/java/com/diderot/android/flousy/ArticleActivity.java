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

import flousy.gui.actionbar.ActionBar;
import flousy.gui.app.KeyboardManager;

public class ArticleActivity extends MotherActivity {

    public static final String EXTRA_ACTIVITY_COLOR = "ACTIVITY_COLOR";
    public static final String EXTRA_CATEGORY_NAME = "CATEGORY_NAME";
    public static final String EXTRA_ARTICLE_NAME = "ARTICLE_NAME";

    private class ViewHolder {
        public EditText nameEditText, priceEditText;
    }

    private ViewHolder formArticle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.form_article_layout);

        if(getIntent().hasExtra(EXTRA_ACTIVITY_COLOR)) {
            int activityColorId = getIntent().getIntExtra(EXTRA_ACTIVITY_COLOR, APP_COLOR);

            //Set ActivityColor immediately after content view
            setActivityColor(activityColorId);
        }

        //Set CustomActionBar
        ActionBar actionBar = getCustomActionBar();
        actionBar.getTitleView().setText(getResources().getString(R.string.activity_article_name));

        //Set ActivityContent
        this.formArticle = new ViewHolder();

        this.formArticle.nameEditText = (EditText) findViewById(R.id.form_article_edittext_name);
        this.formArticle.priceEditText = (EditText) findViewById(R.id.form_article_edittext_price);

        if(getIntent().hasExtra(EXTRA_CATEGORY_NAME)) {
            String categoryName = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);

            actionBar.getTitleView().setText(categoryName);
        }

        if(getIntent().hasExtra(EXTRA_ARTICLE_NAME)) {
            String nameArticle = getIntent().getStringExtra(EXTRA_ARTICLE_NAME);

            actionBar.getSubTitleView().setText(nameArticle);
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

            this.formArticle.nameEditText.setOnEditorActionListener(listener);
            this.formArticle.priceEditText.setOnEditorActionListener(listener);

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
                    if (formArticle.nameEditText.getText().toString().trim().length() > 0
                            && formArticle.priceEditText.getText().toString().trim().length() > 0) {
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
    protected void onDestroy() {
        super.onDestroy();

        getIntent().removeExtra(EXTRA_ACTIVITY_COLOR);
        getIntent().removeExtra(EXTRA_CATEGORY_NAME);
        getIntent().removeExtra(EXTRA_ARTICLE_NAME);
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
        String name = this.formArticle.nameEditText.getEditableText().toString().trim();
        String price = this.formArticle.priceEditText.getEditableText().toString().trim();

        //TODO

        onBackPressed();
    }

    public void loadArticle() {
        String name = "Name ";
        String price = "Price";

        //TODO

        this.formArticle.nameEditText.setText(name, TextView.BufferType.EDITABLE);
        this.formArticle.priceEditText.setText(price, TextView.BufferType.EDITABLE);
    }

    public void updateArticle() {
        String name = this.formArticle.nameEditText.getEditableText().toString().trim();
        String price = this.formArticle.priceEditText.getEditableText().toString().trim();

        //TODO
    }

    public void deleteArticle() {

    }

    public void shareArticle() {

    }
}