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

    public SharedPreferences getSetting(String type) {
        if(type.compareTo(TYPE_PREFS_SESSION) == 0 || type.compareTo(TYPE_PREFS_DATA) == 0) {
            return this.context.getSharedPreferences(type, Context.MODE_PRIVATE);
        }

        return null;
    }

    public boolean signUp(User user) {
        //Add implementation database query

        String verifPassword = this.getSetting(TYPE_PREFS_DATA).getString(user.getEmail(), null);
        if(verifPassword != null) {
            //throw exception user
        } else {
            SharedPreferences.Editor editor = this.getSetting(TYPE_PREFS_DATA).edit(); //database query
            editor.putString(user.getEmail(), user.getPassword());

            return editor.commit();
        }

        return false;
    }

    public boolean checkUserLogged() {
        String verifEmail = this.getSetting(TYPE_PREFS_SESSION).getString(KEY_EMAIL, null);
        if(verifEmail == null) {
            // throw exception user
        } else {
            return true;
        }

        return false;
    }

    public boolean connect(String email, String password) {
        String verifPassword = this.getSetting(TYPE_PREFS_DATA).getString(email, null); //database query
        if(verifPassword == null || verifPassword.compareTo(password) != 0) {
            // throw exception user
        } else {
            SharedPreferences.Editor editor = this.getSetting(TYPE_PREFS_SESSION).edit();
            editor.putString(KEY_EMAIL, email);

            return editor.commit();
        }

        return false;
    }

    public boolean deconnect() {
        boolean contains = this.getSetting(TYPE_PREFS_SESSION).contains(KEY_EMAIL);

        if(contains){
            SharedPreferences.Editor editor = this.getSetting(TYPE_PREFS_SESSION).edit();
            editor.remove(KEY_EMAIL);

            return editor.commit();
        }

        return false;
    }
}
