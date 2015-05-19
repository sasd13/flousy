package flousy.tool;

import android.content.Context;
import android.content.SharedPreferences;

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

import flousy.beans.Utilisateurs;
import flousy.content.user.User;

/**
 * Created by Samir on 15/03/2015.
 */
public class Session {

    private static final String PREFS_SESSION = "PrefsSession";
    private static final String SESSION_KEY = "email";

    private Context context;

    public Session(Context context) {
        this.context = context;
    }

    private SharedPreferences getSettings() {
        return this.context.getSharedPreferences(PREFS_SESSION, Context.MODE_PRIVATE);
    }

    public boolean isUserLogged() {
        return getSettings().contains(SESSION_KEY);
    }

    public String getUserEmail() {
        return getSettings().getString(SESSION_KEY, null);
    }

    public User logIn(String email, String password) {
        //Database query
        //DataManager data = new DataManager(this.context);
        //User user = data.getUser(email);
        Utilisateurs utilisateur = connectUserHTTPPost(email, password);
        User user = new User();
        user.setFirstName(utilisateur.getNom());
        user.setPhoneNumber(utilisateur.getNumTel());
        user.setConnceted(utilisateur.isConnected());
        //End query


       /* if(user == null || user.getPassword().compareTo(password) != 0) {
            return false;
        }
*/
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(SESSION_KEY, email);
        return user;

        // return editor.commit();
    }

    public boolean logOut() {
        boolean contains = getSettings().contains(SESSION_KEY);
        if (contains == false) {
            return false;
        }

        SharedPreferences.Editor editor = getSettings().edit();
        editor.remove(SESSION_KEY);

        return editor.commit();
    }

    public boolean updateSession(String email) {
        SharedPreferences.Editor editor = getSettings().edit();
        editor.remove(SESSION_KEY);
        editor.putString(SESSION_KEY, email);

        return editor.commit();
    }

    public Utilisateurs connectUserHTTPPost(String email, String password) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = "http://10.0.2.2:8080/WebProject/UserConnect";

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
            //resultat.setText(IMCResult.getResult());

            return utilisateursResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }





}
