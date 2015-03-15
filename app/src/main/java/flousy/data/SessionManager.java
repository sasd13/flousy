package flousy.data;

import android.content.Context;
import android.content.SharedPreferences;

import flousy.user.User;

/**
 * Created by Samir on 15/03/2015.
 */
public class SessionManager extends UserManager {
    private static final String PREFS_SESSION = "PrefsSession";

    private static final String SESSION_KEY_EMAIL = "email";

    public SessionManager(Context context) {
        super(context);
    }

    private SharedPreferences getSettings() {
        return super.getSettings(PREFS_SESSION);
    }

    public boolean checkUserEmail() {
        return getSettings().contains(SESSION_KEY_EMAIL);
    }

    public String getUserEmail() {
        return getSettings().getString(SESSION_KEY_EMAIL, null);
    }

    public boolean connect(String email, String password) {
        DataManager data = (DataManager) super.getManager(TYPE_DATA); //database query

        User user = data.getUser(email);
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
