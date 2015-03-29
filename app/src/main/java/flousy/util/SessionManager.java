package flousy.util;

import android.content.Context;
import android.content.SharedPreferences;

import flousy.content.user.User;

/**
 * Created by Samir on 15/03/2015.
 */
public class SessionManager {

    private static final String PREFS_SESSION = "PrefsSession";
    private static final String SESSION_KEY_EMAIL = "email";

    private Context context;

    public SessionManager(Context context) {
        this.context = context;
    }

    private SharedPreferences getSettings() {
        return this.context.getSharedPreferences(PREFS_SESSION, Context.MODE_PRIVATE);
    }

    public boolean checkUserEmail() {
        return getSettings().contains(SESSION_KEY_EMAIL);
    }

    public String getUserEmail() {
        return getSettings().getString(SESSION_KEY_EMAIL, null);
    }

    public boolean connect(String email, String password) {
        //Database query
        DataManager data = new DataManager(this.context);
        User user = data.getUser(email);
        //End query

        if(user == null || user.getPassword().compareTo(password) != 0) {
            return false;
        }

        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(SESSION_KEY_EMAIL, email);

        return editor.commit();
    }

    public boolean deconnect() {
        boolean contains = getSettings().contains(SESSION_KEY_EMAIL);
        if(contains == false) {
            return false;
        }

        SharedPreferences.Editor editor = getSettings().edit();
        editor.remove(SESSION_KEY_EMAIL);

        return editor.commit();
    }

    public boolean updateSession(String email) {
        SharedPreferences.Editor editor = getSettings().edit();
        editor.remove(SESSION_KEY_EMAIL);
        editor.putString(SESSION_KEY_EMAIL, email);

        return editor.commit();
    }
}
