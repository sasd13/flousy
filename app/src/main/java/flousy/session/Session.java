package flousy.session;

import android.content.Context;
import android.content.SharedPreferences;

import flousy.beans.core.Account;
import flousy.beans.core.User;
import flousy.db.DBManager;
import flousy.db.DataAccessor;

/**
 * Created by Samir on 15/03/2015.
 */
public class Session {

    private static final String SESSION_PREFERENCES = "session_preferences";
    private static final String SESSION_USER_ID = "session_user_id";
    private static final String SESSION_ACCOUNT_ID = "session_account_id";

    private static SharedPreferences preferences;
    private static DataAccessor dao;

    protected Session() {}
    
    public static void start(Context context) {
        preferences = context.getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE);
        dao = DBManager.getDao();
    }

    public static boolean isUserLoggedIn() {
        return preferences.contains(SESSION_USER_ID);
    }

    public static String getUserId() {
        return preferences.getString(SESSION_USER_ID, null);
    }

    public static String getAccountId() {
        return preferences.getString(SESSION_ACCOUNT_ID, null);
    }

    public static boolean logIn(String email, String password) {
        User user = dao.selectUserByEmail(email);

        try {
            if (user.getPassword().equals(password)) {
                Account account = dao.selectAccountByUser(user.getId());

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(SESSION_USER_ID, String.valueOf(user.getId()));
                editor.putString(SESSION_ACCOUNT_ID, String.valueOf(account.getId()));

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
