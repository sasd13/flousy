package flousy.user;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Samir on 07/03/2015.
 */
public class UserManager {

    private static final String TYPE_PREFS_SESSION = "PrefsSession";
    private static final String TYPE_PREFS_DATA = "PrefsData";
    private static final String KEY_EMAIL = "email";

    private Context context;

    public UserManager(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private SharedPreferences getSetting(String type) {
        if(type.compareTo(TYPE_PREFS_SESSION) != 0 && type.compareTo(TYPE_PREFS_DATA) != 0) {
            return null;
        }

        return this.context.getSharedPreferences(type, Context.MODE_PRIVATE);
    }

    public boolean signUp(User user) {
        boolean signed = this.getSetting(TYPE_PREFS_DATA).contains(user.getEmail());
        if(signed == true) {
            return false;
        }

        SharedPreferences.Editor editor = this.getSetting(TYPE_PREFS_DATA).edit(); //database query
        editor.putString(user.getEmail(), user.getPassword());

        return editor.commit();
    }

    public boolean checkUserLogged() {
        return this.getSetting(TYPE_PREFS_SESSION).contains(KEY_EMAIL);
    }

    public boolean connect(String email, String password) {
        String verifPassword = this.getSetting(TYPE_PREFS_DATA).getString(email, null); //database query
        if(verifPassword == null || verifPassword.compareTo(password) != 0) {
            return false;
        }

        SharedPreferences.Editor editor = this.getSetting(TYPE_PREFS_SESSION).edit();
        editor.putString(KEY_EMAIL, email);

        return editor.commit();
    }

    public boolean deconnect() {
        boolean contains = this.getSetting(TYPE_PREFS_SESSION).contains(KEY_EMAIL);
        if(contains == false) {
            return false;
        }

        SharedPreferences.Editor editor = this.getSetting(TYPE_PREFS_SESSION).edit();
        editor.remove(KEY_EMAIL);

        return editor.commit();
    }
}
