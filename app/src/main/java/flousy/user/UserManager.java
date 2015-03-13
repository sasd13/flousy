package flousy.user;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Samir on 07/03/2015.
 */
public class UserManager {

    private static UserManager instance = null;

    private static Context context = null;
    private SharedPreferences settings;
    private static final String PREFS_NAME = "PrefsFile";
    private static final String KEY_LOGIN = "login";

    private UserManager() {
        this.settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context ctxt) {
        context = ctxt;
    }

    public static synchronized UserManager getInstance() {
        if(instance == null) {
            instance = new UserManager();
        }

        return instance;
    }

    public boolean signUp(User user) {
        return false;
    }

    public String checkUserLogged() {
        return this.settings.getString(KEY_LOGIN, null);
    }

    public User connect(String login, String password) {
        User user = null;

        String verifEmail = "abcd@email.com";
        String verifPassword = "password";

        if((login.trim().compareTo(verifEmail) == 0) && (password.trim().compareTo(verifPassword) == 0)) {
            user = new User();
            user.setEmail(login);
            user.setPassword(password);

            SharedPreferences.Editor editor = this.settings.edit();
            editor.putString(KEY_LOGIN, login);
            editor.commit();
        }

        return user;
    }

    public boolean deconnect() {
        if(this.settings.contains(KEY_LOGIN)){
            SharedPreferences.Editor editor = this.settings.edit();
            editor.remove(KEY_LOGIN);
            editor.commit();

            return true;
        }

        return false;
    }
}
