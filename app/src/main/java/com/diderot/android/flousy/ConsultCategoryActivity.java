package com.diderot.android.flousy;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import flousy.beans.Produit;
import flousy.gui.actionbar.ActionBar;
import flousy.gui.recycler.tab.Tab;
import flousy.gui.recycler.tab.TabItem;
import flousy.gui.recycler.tab.TabItemTitle;
import flousy.tool.Session;

public class ConsultCategoryActivity extends MotherActivity {

    private Tab tabArticles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set ActivityContent
        setContentView(R.layout.recyclerview);

        if(getIntent().hasExtra(EXTRA_ACTIVITY_COLOR)) {
            int activityColorId = getIntent().getIntExtra(EXTRA_ACTIVITY_COLOR, APP_COLOR);

            //Set ActivityColor immediately after content view
            setActivityColor(activityColorId);
        }

        //Set CustomActionBar
        ActionBar actionBar = getCustomActionBar();
        actionBar.getTitleView().setText(R.string.activity_spends_name);

        if(getIntent().hasExtra(EXTRA_CATEGORY_NAME)) {
            String categoryName = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);

            actionBar.getTitleView().setText(categoryName);
        }

        //Set Activity content
        RecyclerView tabView = (RecyclerView) findViewById(R.id.recyclerview);
        this.tabArticles = new Tab(this);
        this.tabArticles.adapt(tabView);

        //Add items
        addArticlesTabItems();

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

    public void addArticlesTabItems() {
        TabItemTitle tabItemTitle = new TabItemTitle();
        this.tabArticles.addItem(tabItemTitle);

        TabItem tabItem;

        Resources resources = getResources();
        WebService webService = new WebService(this);
        String nameArticle;
        String priceArticle;
        Intent intent;
        Session session = new Session(this);
        String emailUser =session.getUserEmail();
        int idUser = webService.chercherUtilisateur(emailUser);


         // =  new ArrayList<>();
        ArrayList<Produit>   listProduit = webService.listProduit(idUser);
        tabItem = new TabItem();


        for(int i=0; i<listProduit.size(); i++) {

            intent = new Intent(this, ArticleActivity.class);
                    nameArticle =  listProduit.get(i).getNom();
                    Float prix =  listProduit.get(i).getPrix();
                      priceArticle=  Float.toString(prix);
            tabItem.setNameText(nameArticle);
            tabItem.setPriceText(priceArticle);
            intent.putExtra(EXTRA_ACTIVITY_COLOR, getActivityColor());
            intent.putExtra(EXTRA_ARTICLE_NAME, nameArticle);
            intent.putExtra(EXTRA_ARTICLE_ID, listProduit.get(i).getIdProduit());
            tabItem.setIntent(intent);
            }
        this.tabArticles.addItem(tabItem);

        }








}