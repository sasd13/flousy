package com.diderot.android.flousy;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import flousy.beans.Categorie;
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

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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
       ArrayList<Categorie> allcategorie= allCategoriesHttpClient();
       //System.out.println(allcategorie.get(0).getNom());

        for(int i=0; i<6; i++) {
            gridItem = new GridItem();
            gridItem.setColor(getActivityColor());
            gridItem.setIntent(null);

            switch(i) {
                case 0:
                    gridItem.setText(allcategorie.get(i).getNom());
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_food));
                    break;
                case 1:
                    gridItem.setText(allcategorie.get(i).getNom());
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_drug));
                    break;
                case 2:
                    gridItem.setText(allcategorie.get(i).getNom());
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_transport));
                    break;
                case 3:
                    gridItem.setText(allcategorie.get(i).getNom());
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_controller));
                    break;
                case 4:
                    gridItem.setText(allcategorie.get(i).getNom());
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_clothes));
                    break;
                case 5:
                    gridItem.setText(allcategorie.get(i).getNom());
                    gridItem.setImage(resources.getDrawable(R.drawable.griditem_shopping));
                    break;
            }

            this.gridCategories.addItem(gridItem);
        }
    }
    public ArrayList<Categorie> allCategoriesHttpClient() {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = "http://10.0.2.2:8080/WebProject/allCategories";

            HttpPost post = new HttpPost(url);
            // add header


           Gson gson = new GsonBuilder().setPrettyPrinting().create();

            CloseableHttpResponse response = httpclient.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuffer donnee = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                donnee.append(line);
            }
            Type type = new TypeToken<List<Categorie>>(){}.getType();

            // type de retour de Gson
            ArrayList<Categorie> allCategorie = gson.fromJson(donnee.toString(), type);
            //resultat.setText(IMCResult.getResult());

            return allCategorie;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}