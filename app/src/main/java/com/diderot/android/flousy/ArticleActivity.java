package com.diderot.android.flousy;

import android.content.Intent;
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
    private String  categoryName, articleName;
    private int articleId,categoryId;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.form_article_layout);

        if(getIntent().hasExtra(EXTRA_ACTIVITY_COLOR)) {
            int activityColor = getIntent().getIntExtra(EXTRA_ACTIVITY_COLOR, APP_COLOR);

            //Set ActivityColor immediately after content view
            setActivityColor(activityColor);
        }

        //Set CustomActionBar
        ActionBar actionBar = getCustomActionBar();
        actionBar.getTitleView().setText(getResources().getString(R.string.activity_article_name));

        //Set ActivityContent
        this.formArticle = new ViewHolder();

        this.formArticle.nameEditText = (EditText) findViewById(R.id.form_article_edittext_name);
        this.formArticle.priceEditText = (EditText) findViewById(R.id.form_article_edittext_price);

        if(getIntent().hasExtra(EXTRA_CATEGORY_ID)) {
            categoryId = getIntent().getIntExtra(EXTRA_CATEGORY_ID,1);
        }

        if(getIntent().hasExtra(EXTRA_CATEGORY_NAME)) {
            categoryName = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);

            actionBar.getTitleView().setText(categoryName);
        }

        if(getIntent().hasExtra(EXTRA_ARTICLE_ID)) {
            articleId = getIntent().getIntExtra(EXTRA_ARTICLE_ID,0);
        }

        if(getIntent().hasExtra(EXTRA_ARTICLE_NAME)) {
            articleName = getIntent().getStringExtra(EXTRA_ARTICLE_NAME);

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
    
    Session session = new Session(this);
    WebService webService = new WebService(this);
    
    public void addArticle() {
        String name = this.formArticle.nameEditText.getEditableText().toString().trim();
        String price = this.formArticle.priceEditText.getEditableText().toString().trim();

        String emailUser=session.getUserEmail();
        int idUtilisateur=webService.chercherUtilisateur(emailUser);
        Float prix= Float.parseFloat(price);
        int idCategorie = webService.chercherproduitId(categoryName);
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
                    Intent intent = new Intent(this, NewActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(intent);
                    finish();
                }
            }
        }


    }

    public void loadArticle() {
        String price = "price";
        String name = "name";
      

        Produit p =webService.produitByid(articleId);
         float prix=p.getPrix();
         name= p.getNom();
         price =Float.toString(prix);

        this.formArticle.nameEditText.setText(name, TextView.BufferType.EDITABLE);
        this.formArticle.priceEditText.setText(price, TextView.BufferType.EDITABLE);
    }

    public void updateArticle() {
        String name = this.formArticle.nameEditText.getEditableText().toString().trim();
        String price = this.formArticle.priceEditText.getEditableText().toString().trim();

        Produit p= new Produit();
        p.setIdProduit(articleId);
        p.setPrix(Float.parseFloat(price));
        p.setNom(name);
        p.setIdCategorie(categoryId);
        if(webService.updateProduit(p)) {
            backToLastActivity();
        }
    }

    public void backToLastActivity() {
        Intent intent = new Intent(this, ConsultCategoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(EXTRA_ACTIVITY_COLOR, getActivityColor());
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);

        startActivity(intent);
        finish();
    }

    public void deleteArticle() {
        //int idArticle = Integer.parseInt(articleId);
        if(webService.supprimerProduit(articleId)) {
            backToLastActivity();
        }
    }

    public void shareArticle() {
        //TODO
        //articleId

    }



}