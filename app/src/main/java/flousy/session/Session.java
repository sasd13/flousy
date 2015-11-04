package flousy.session;

import android.content.Context;
import android.content.SharedPreferences;

import flousy.content.customer.Customer;
import flousy.db.DBManager;
import flousy.db.DBAccessor;

/**
 * Created by Samir on 15/03/2015.
 */
public class Session {

    private static final String SESSION_PREFERENCES = "session_preferences";
    private static final String SESSION_USER_ID = "session_user_id";

    private static SharedPreferences preferences;
    private static DBAccessor dao;

    protected Session() {}
    
    public static void start(Context context) {
        preferences = context.getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE);
        dao = DBManager.getDao();
    }

    public static boolean isUserLogged() {
        return preferences.contains(SESSION_USER_ID);
    }

    public static String getUserId() {
        return preferences.getString(SESSION_USER_ID, null);
    }

    public static boolean logIn(String email, String password) {
        dao.open();
        Customer customer = dao.selectCustomerByEmail(email);
        dao.close();

        try {
            if(customer.getPassword().equals(password)) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(SESSION_USER_ID, String.valueOf(customer.getId()));

                return editor.commit();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean logOut() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(SESSION_USER_ID);

        return editor.commit();
    }
}
