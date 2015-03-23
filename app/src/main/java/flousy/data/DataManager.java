package flousy.data;

import android.content.Context;
import android.content.SharedPreferences;

import flousy.content.user.User;

/**
 * Created by Samir on 15/03/2015.
 */
public class DataManager extends UserManager{
    private static final String PREFS_DATA = "PrefsData";

    private static final String DATA_KEY_FIRSTNAME = "first_name";
    private static final String DATA_KEY_LASTNAME = "last_name";
    private static final String DATA_KEY_PHONENUMBER = "phone_number";
    private static final String DATA_KEY_EMAIL = "email";
    private static final String DATA_KEY_PASSWORD = "password";

    public DataManager(Context context) {
        super(context);
    }

    @Override
    protected SharedPreferences getSettings(String email) {
        String login = email.substring(0, email.indexOf('@'));
        return super.getSettings(PREFS_DATA + "_" + login);
    }

    public boolean signUp(User user) {
       // String verifEmail = getSettings(user.getEmail()).getString(user.getEmail(), null);
       // if(verifEmail != null) {
         //   return false;
       // }

        boolean signed = setUser(user.getEmail(), user);

        return signed;
    }

    public User getUser(String email) {
        String verifEmail = getSettings(email).getString(DATA_KEY_EMAIL, null);
        if(verifEmail == null || verifEmail.compareTo(email) != 0) {
            return null;
        }

        String firstName = getSettings(email).getString(DATA_KEY_FIRSTNAME, null);
        String lastName = getSettings(email).getString(DATA_KEY_LASTNAME, null);
        String phoneNumber = getSettings(email).getString(DATA_KEY_PHONENUMBER, null);
        String password = getSettings(email).getString(DATA_KEY_PASSWORD, null);

        return new User(firstName, lastName, null, email, password, null);
    }

    public boolean setUser(String email, User user) {
        SharedPreferences.Editor editor = getSettings(email).edit();

        editor.putString(DATA_KEY_FIRSTNAME, user.getFirstName());
        editor.putString(DATA_KEY_LASTNAME, user.getLastName());
        editor.putString(DATA_KEY_PHONENUMBER, user.getPhoneNumber());
        editor.putString(DATA_KEY_EMAIL, user.getEmail());
        editor.putString(DATA_KEY_PASSWORD, user.getPassword());

        return editor.commit();
    }
}
