package flousy.user;

import android.content.SharedPreferences;

/**
 * Created by Samir on 07/03/2015.
 */
public class SessionManager {

    private static final String KEY_LOGIN = "login";

    private SessionManager() { }

    public static boolean signup(User user) {
        return false;
    }

    public static User connect(SharedPreferences settings, String login, String password) {
        User user = null;

        String verifEmail = "abcd@email.com";
        String verifPassword = "password";

        if((login.trim().compareTo(verifEmail) == 0) && (password.trim().compareTo(verifPassword) == 0)) {
            user = new User();
            user.setEmail(login);
            user.setPassword(password);

            SharedPreferences.Editor editor = settings.edit();
            editor.putString(KEY_LOGIN, login);
            editor.commit();
        }

        return user;
    }

    public static boolean deconnect(SharedPreferences settings) {
        if(settings.contains(KEY_LOGIN)){
            SharedPreferences.Editor editor = settings.edit();
            editor.remove(KEY_LOGIN);
            editor.commit();

            return true;
        }

        return false;
    }
}
