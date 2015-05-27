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
        public EditText editTextName, editTextPrice;
    }

    private ViewHolder formArticle;
    private String categoryId, categoryName, articleId, articleName;
    
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

        this.formArticle.editTextName = (EditText) findViewById(R.id.form_article_edittext_name);
        this.formArticle.editTextPrice = (EditText) findViewById(R.id.form_article_edittext_price);

        if(getIntent().hasExtra(EXTRA_CATEGORY_ID)) {
            categoryId = getIntent().getStringExtra(EXTRA_CATEGORY_ID);
        }

        if(getIntent().hasExtra(EXTRA_CATEGORY_NAME)) {
            categoryName = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);

            actionBar.getTitleView().setText(categoryName);
        }

        if(getIntent().hasExtra(EXTRA_ARTICLE_ID)) {
            articleId = getIntent().getStringExtra(EXTRA_ARTICLE_ID);
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
        String name = this.formArticle.editTextName.getEditableText().toString().trim();
        String price = this.formArticle.editTextPrice.getEditableText().toString().trim();

        Session session = new Session(this);
        String emailUser=session.getUserEmail();
        int idUtilisateur=chercherUtilisateur(emailUser);
        Float prix= Float.parseFloat(price);
        int idCategorie = chercherproduitId(categoryId);
        Produit produit = new Produit();
        produit.setNom(name);
        produit.setPrix(prix);
        produit.setIdCategorie(idCategorie);


        if(idCategorie!=-1)
        {
            int idProduit=ajouterArticle(produit);
            if(idProduit!=-1) {
                produit.setIdProduit(idProduit);
                ProduitUtilisateur produitUtilisateur= new ProduitUtilisateur();
                produitUtilisateur.setIdCategorie(idCategorie);
                produitUtilisateur.setIdProduit(idProduit);
                produitUtilisateur.setIdUtilisateur(idUtilisateur);

                if(ajoutProduitUser(produitUtilisateur)){
                    onBackPressed();

                }
            }
        }


    }

    public void loadArticle() {
        String name = "Name ";
        String price = "Price";

        //TODO

        this.formArticle.editTextName.setText(name, TextView.BufferType.EDITABLE);
        this.formArticle.editTextPrice.setText(price, TextView.BufferType.EDITABLE);
    }

    public void updateArticle() {
        String name = this.formArticle.editTextName.getEditableText().toString().trim();
        String price = this.formArticle.editTextPrice.getEditableText().toString().trim();

        //TODO
    }

    public void deleteArticle() {

    }

    public void shareArticle() {

    }
    public int ajouterArticle(Produit produit) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = "http://10.0.2.2:8080/WebProject/InsertionProduit";

            HttpPost post = new HttpPost(url);
            // add heade

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(produit);

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair(produit.JSON_PRODUIT_PARAMETER_NAME, jsonString));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            CloseableHttpResponse response = httpclient.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuffer donnee = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                donnee.append(line);
            }

            int ajoutProduit = gson.fromJson(donnee.toString(), int.class);
            return ajoutProduit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public Boolean ajoutProduitUser(ProduitUtilisateur produitUtilisateur) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = "http://10.0.2.2:8080/WebProject/AjoutProduitUtilisateur";

            HttpPost post = new HttpPost(url);
            // add header

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(produitUtilisateur);

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair(ProduitUtilisateur.JSON_PRODUIT_USER_NAME, jsonString));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            CloseableHttpResponse response = httpclient.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuffer donnee = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                donnee.append(line);
            }

            Boolean produitUt = gson.fromJson(donnee.toString(),Boolean.class);
            //resultat.setText(IMCResult.getResult());
            return produitUt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public int chercherproduitId(String nom) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = "http://10.0.2.2:8080/WebProject/ChercherProduit";

            HttpPost post = new HttpPost(url);
            // add header

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("produitnom", nom));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            CloseableHttpResponse response = httpclient.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuffer donnee = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                donnee.append(line);
            }

            int produitId = gson.fromJson(donnee.toString(),int.class);
            //resultat.setText(IMCResult.getResult());
            return produitId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    public int chercherUtilisateur(String email) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = "http://10.0.2.2:8080/WebProject/ChercherUtilisateurProduit";

            HttpPost post = new HttpPost(url);
            // add header

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("emailUtilisateur", email));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            CloseableHttpResponse response = httpclient.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuffer donnee = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                donnee.append(line);
            }

            int utilisateurtId = gson.fromJson(donnee.toString(),int.class);
            //resultat.setText(IMCResult.getResult());
            return utilisateurtId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


}