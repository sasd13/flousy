package flousy.user;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Samir on 07/03/2015.
 */
public class SessionManager {

    private static SessionManager instance = null;

    private Context context;
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private static final String PREFS_NAME = "PrefsFile";
    private static final String KEY_LOGIN = "login";

    private SessionManager(Context context) {
        this.context = context;
        this.settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        this.editor = this.settings.edit();
    }

    public static synchronized SessionManager getInstance(Context context) {
        if(instance == null) {
            instance = new SessionManager(context);
        }

        return instance;
    }

    public boolean signUp(User user) {
        return false;
    }

    public String checkUserLogin() {
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

            this.editor.putString(KEY_LOGIN, login);
            this.editor.commit();
        }

        return user;
    }

    public boolean deconnect() {
        if(this.settings.contains(KEY_LOGIN)){
            this.editor.remove(KEY_LOGIN);
            this.editor.commit();

            return true;
        }

        return false;
    }
}
