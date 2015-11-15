package flousy.session;

import android.content.Context;
import android.content.SharedPreferences;

import flousy.bean.Account;
import flousy.db.DataAccessor;
import flousy.db.DataAccessorFactory;

public class Session {

    private static final String SESSION_PREFERENCES = "session_preferences";
    private static final String SESSION_ACCOUNT_ID = "session_account_id";

    private static SharedPreferences preferences;

    protected Session() {}
    
    public static void start(Context context) {
        preferences = context.getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static boolean isStarted() {
        return preferences.contains(SESSION_ACCOUNT_ID);
    }

    public static long getAccountId() {
        return preferences.getLong(SESSION_ACCOUNT_ID, 0);
    }

    public static boolean logIn(String email, String password) {
        DataAccessor dao = DataAccessorFactory.get();

        Account account = dao.selectAccountByUserEmail(email);

        try {
            if (account.getUserPassword().equals(password)) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong(SESSION_ACCOUNT_ID, account.getId());

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
