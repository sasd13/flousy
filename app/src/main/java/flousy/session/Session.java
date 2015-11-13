package flousy.session;

import android.content.Context;
import android.content.SharedPreferences;

import flousy.beans.User;
import flousy.db.DataAccessor;
import flousy.db.DataAccessorFactory;

public class Session {

    private static final String SESSION_PREFERENCES = "session_preferences";
    private static final String SESSION_USER_EMAIL = "session_user_email";

    private static SharedPreferences preferences;

    protected Session() {}
    
    public static void start(Context context) {
        preferences = context.getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static boolean isUserLoggedIn() {
        return preferences.contains(SESSION_USER_EMAIL);
    }

    public static String getUserEmail() {
        return preferences.getString(SESSION_USER_EMAIL, null);
    }

    public static boolean logIn(String email, String password) {
        DataAccessor dao = DataAccessorFactory.get();

        User user = dao.selectUser(email);

        try {
            if (user.getPassword().equals(password)) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(SESSION_USER_EMAIL, user.getEmail());

                return editor.commit();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean logOut() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();

        return editor.commit();
    }
}
