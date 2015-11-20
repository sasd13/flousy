package flousy.session;

import android.content.Context;
import android.content.SharedPreferences;

import flousy.bean.Customer;
import flousy.db.dao.DAO;
import flousy.db.dao.DAOFactory;

public class Session {

    private static final String SESSION_PREFERENCES = "session_preferences";
    private static final String SESSION_CUSTOMER_ID = "session_customer_id";

    private static SharedPreferences preferences;

    protected Session() {}
    
    public static void start(Context context) {
        preferences = context.getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static boolean isStarted() {
        return preferences.contains(SESSION_CUSTOMER_ID);
    }

    public static long getCustomerId() {
        return preferences.getLong(SESSION_CUSTOMER_ID, 0);
    }

    public static boolean logIn(String email, String password) {
        DAO dao = DAOFactory.get();

        Customer customer = dao.selectCustomerByEmail(email);

        try {
            if (customer.getPassword().equals(password)) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong(SESSION_CUSTOMER_ID, customer.getId());

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
