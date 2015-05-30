package flousy.tool;

import android.content.Context;
import android.content.SharedPreferences;

import com.diderot.android.flousy.R;
import com.diderot.android.flousy.WebService;
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

        WebService webService = new WebService(context);
        Utilisateurs utilisateur = webService.connectUserHTTPPost(email, password);
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
        editor.commit();
        return user;

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
}
