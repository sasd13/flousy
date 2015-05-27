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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import flousy.beans.Produit;
import flousy.beans.ProduitUtilisateur;
import flousy.beans.Utilisateurs;
import flousy.gui.actionbar.ActionBar;
import flousy.gui.app.KeyboardManager;
import flousy.tool.Session;

public class ArticleActivity extends MotherActivity {

    private class ViewHolder {
        public EditText nameEditText, priceEditText;
    }

    private ViewHolder formArticle;
    String categoryName;
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
             categoryName = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);

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
            WebService webService = new WebService(this);
        Session session = new Session(this);
        String emailUser=session.getUserEmail();
        int idUtilisateur=webService.chercherUtilisateur(emailUser);
        Float prix= Float.parseFloat(price);
        int idCategorie= webService.chercherproduitId(categoryName);
        Produit produit = new Produit();
        produit.setNom(name);
        produit.setPrix(prix);
        produit.setIdCategorie(idCategorie);


        if(idCategorie!=-1)
        {
            int idProduit=webService.ajouterArticle(produit);
            if(idProduit!=-1) {
                produit.setIdProduit(idProduit);
                ProduitUtilisateur produitUtilisateur= new ProduitUtilisateur();
                produitUtilisateur.setIdCategorie(idCategorie);
                produitUtilisateur.setIdProduit(idProduit);
                produitUtilisateur.setIdUtilisateur(idUtilisateur);

                if(webService.ajoutProduitUser(produitUtilisateur)){
                    onBackPressed();

                }
            }
        }


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