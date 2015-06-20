package flousy.webservice;

import android.content.Context;

import com.example.flousy.R;
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
import flousy.beans.ProduitUtilisateur;
import flousy.beans.Utilisateurs;

/**
 * Created by simo on 27/05/2015.
 */
public class WebService {
    private Context context;

    public WebService(Context context){
        this.context = context;
    }

    public ArrayList<Produit> ProduitCategorie(int idUtilisateur,int idCategorie) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = this.context.getResources().getString(R.string.adressConnect)+"/AfficherProduitUtilisateur";

            HttpPost post = new HttpPost(url);
            // add header
            String idUser = Integer.toString(idUtilisateur);
            String idCate = Integer.toString(idCategorie);


            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("idUtilisateur",idUser ));
            urlParameters.add(new BasicNameValuePair("idCategorie",idCate ));


            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            CloseableHttpResponse response = httpclient.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuffer donnee = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                donnee.append(line);
            }

            Type listType = new TypeToken<ArrayList<Produit>>(){}.getType();
            // ArrayList<T> list = g.fromJson(s, listType);
            ArrayList<Produit> listproduit = gson.fromJson(donnee.toString(),listType);
            //resultat.setText(IMCResult.getResult());
            return listproduit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Produit> listProduit(int idUtilisateur) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = this.context.getResources().getString(R.string.adressConnect)+"/AfficherProduitUtilisateur";

            HttpPost post = new HttpPost(url);
            // add header
            String idUser = Integer.toString(idUtilisateur);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("idUtilisateur",idUser ));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            CloseableHttpResponse response = httpclient.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuffer donnee = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                donnee.append(line);
            }

            Type listType = new TypeToken<ArrayList<Produit>>(){}.getType();
            // ArrayList<T> list = g.fromJson(s, listType);
            ArrayList<Produit> listproduit = gson.fromJson(donnee.toString(),listType);
            //resultat.setText(IMCResult.getResult());
            return listproduit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public int ajouterArticle(Produit produit) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = this.context.getResources().getString(R.string.adressConnect)+"/InsertionProduit";

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
            String url = this.context.getResources().getString(R.string.adressConnect)+"/AjoutProduitUtilisateur";

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
            String url = this.context.getResources().getString(R.string.adressConnect)+"/ChercherProduit";

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

    public Produit produitByid(int idProduit) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = this.context.getResources().getString(R.string.adressConnect)+"/ChercherProduitID";

            HttpPost post = new HttpPost(url);
            // add header
            Produit produit = new Produit();
            produit.setIdProduit(idProduit);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(produit);

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair(produit.JSON_PRODUIT_PARAMETER_NAME, jsonString));
           // urlParameters.add(new BasicNameValuePair(utilisateur.JSON_USER_PARAMETER_NAME, jsonString));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            CloseableHttpResponse response = httpclient.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuffer donnee = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                donnee.append(line);
            }

            Produit produitres = gson.fromJson(donnee.toString(),Produit.class);
            return produitres;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean supprimerProduit(int idProduit) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = this.context.getResources().getString(R.string.adressConnect)+"/SupressionProduit";

            HttpPost post = new HttpPost(url);
            // add header
            String idpr=Integer.toString(idProduit);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("idProduit", idpr));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            CloseableHttpResponse response = httpclient.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuffer donnee = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                donnee.append(line);
            }

            Boolean produitId = gson.fromJson(donnee.toString(),Boolean.class);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int chercherUtilisateur(String email) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = this.context.getResources().getString(R.string.adressConnect)+"/ChercherUtilisateurProduit";

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

    public Boolean inscriptionUserHTTPPost(Utilisateurs utilisateur) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = this.context.getResources().getString(R.string.adressConnect)+"/InscriptionUtilisateur";

            HttpPost post = new HttpPost(url);
            // add heade

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(utilisateur);

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair(utilisateur.JSON_USER_PARAMETER_NAME, jsonString));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            CloseableHttpResponse response = httpclient.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuffer donnee = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                donnee.append(line);
            }

            Boolean utilisateursInsert = gson.fromJson(donnee.toString(), Boolean.class);
            return utilisateursInsert;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existUserHTTPPost(String email) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = this.context.getResources().getString(R.string.adressConnect)+"/ExistUtilisateur";

            HttpPost post = new HttpPost(url);
            // add header

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("userEmail", email));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            CloseableHttpResponse response = httpclient.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuffer donnee = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                donnee.append(line);
            }

            Boolean exitUser = gson.fromJson(donnee.toString(),Boolean.class);
            //resultat.setText(IMCResult.getResult());
            return exitUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Utilisateurs connectUserHTTPPost(String email, String password) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url =this.context.getResources().getString(R.string.adressConnect)+"/UserConnect";

            HttpPost post = new HttpPost(url);
            // add header

            Utilisateurs utilisateur = new Utilisateurs();
            utilisateur.setEmail(email);
            utilisateur.setPassword(password);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(utilisateur);

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair(utilisateur.JSON_USER_PARAMETER_NAME, jsonString));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            CloseableHttpResponse response = httpclient.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuffer donnee = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                donnee.append(line);
            }

            Utilisateurs utilisateursResult = gson.fromJson(donnee.toString(), Utilisateurs.class);

            return utilisateursResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean updateProduit(Produit produit) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = this.context.getResources().getString(R.string.adressConnect)+"/UpdateProduit";

            HttpPost post = new HttpPost(url);
            // add header
            int produitId=produit.getIdProduit();
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

            Boolean produitU = gson.fromJson(donnee.toString(), Boolean.class);
            return produitU;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
