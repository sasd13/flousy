package flousy.session;

import android.content.Context;
import android.content.SharedPreferences;

import flousy.beans.core.Account;
import flousy.beans.core.User;
import flousy.db.DataAccessor;
import flousy.db.DataAccessorFactory;

/**
 * Created by Samir on 15/03/2015.
 */
public class Session {

    private static final String SESSION_PREFERENCES = "session_preferences";
    private static final String SESSION_USER_ID = "session_user_id";

    private static SharedPreferences preferences;

    protected Session() {}
    
    public static void start(Context context) {
        preferences = context.getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static boolean isUserLoggedIn() {
        return preferences.contains(SESSION_USER_ID);
    }

    public static long getUserId() {
        return preferences.getLong(SESSION_USER_ID, 0);
    }

    public static boolean logIn(String email, String password) {
        DataAccessor dao = DataAccessorFactory.get();

        User user = dao.selectUserByEmail(email);

        try {
            if (user.getPassword().equals(password)) {
                Account account = dao.selectAccountByUser(user.getId());

                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong(SESSION_USER_ID, user.getId());

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
