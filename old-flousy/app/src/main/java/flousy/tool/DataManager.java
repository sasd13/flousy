package flousy.tool;

import android.content.Context;
import android.content.SharedPreferences;

import flousy.content.user.User;

/**
 * Created by Samir on 15/03/2015.
 */
public class DataManager {

    private static final String PREFS_DATA = "PrefsData";
    private static final String DATA_KEY_FIRSTNAME = "first_name";
    private static final String DATA_KEY_LASTNAME = "last_name";
    private static final String DATA_KEY_PHONENUMBER = "phone_number";
    private static final String DATA_KEY_EMAIL = "email";
    private static final String DATA_KEY_PASSWORD = "password";

    private Context context;

    public DataManager(Context context) {
        this.context = context;
    }

    protected SharedPreferences getSettings(String email) {
        String login = email.substring(0, email.indexOf('@'));

        return this.context.getSharedPreferences(PREFS_DATA + "_" + login, Context.MODE_PRIVATE);
    }

    public boolean signUp(User user) {
        boolean signed = false;

        //Database query
        String verifEmail = getSettings(user.getEmail()).getString(user.getEmail(), null);
        if(verifEmail != null) {
            return false;
        }

        signed = setUser(user.getEmail(), user);
        //End query

        return signed;
    }

    public User getUser(String email) {
        User user = null;

        //Database query
        String verifEmail = getSettings(email).getString(DATA_KEY_EMAIL, null);
        if(verifEmail == null || verifEmail.compareTo(email) != 0) {
            return null;
        }

        String firstName = getSettings(email).getString(DATA_KEY_FIRSTNAME, null);
        String lastName = getSettings(email).getString(DATA_KEY_LASTNAME, null);
        String phoneNumber = getSettings(email).getString(DATA_KEY_PHONENUMBER, null);
        String password = getSettings(email).getString(DATA_KEY_PASSWORD, null);

        user = new User(firstName, lastName, null, email, password, null);
        //End query

        return user;
    }

    public boolean setUser(String email, User user) {
        boolean updated = false;

        //Database query
        SharedPreferences.Editor editor = getSettings(email).edit();

        editor.putString(DATA_KEY_FIRSTNAME, user.getFirstName());
        editor.putString(DATA_KEY_LASTNAME, user.getLastName());
        editor.putString(DATA_KEY_PHONENUMBER, user.getPhoneNumber());
        editor.putString(DATA_KEY_EMAIL, user.getEmail());
        editor.putString(DATA_KEY_PASSWORD, user.getPassword());

        updated = editor.commit();
        //End query

        return updated;
    }
}
