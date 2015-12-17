package flousy.tool;

import android.content.Context;
import android.content.SharedPreferences;

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

    public boolean logIn(String email, String password) {
        //Database query
        DataManager data = new DataManager(this.context);
        User user = data.getUser(email);
        //End query

        if(user == null || user.getPassword().compareTo(password) != 0) {
            return false;
        }

        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(SESSION_KEY, email);

        return editor.commit();
    }

    public boolean logOut() {
        boolean contains = getSettings().contains(SESSION_KEY);
        if(contains == false) {
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
