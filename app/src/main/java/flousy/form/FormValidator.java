package flousy.form;

import android.util.Patterns;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Samir on 13/03/2015.
 */
public class FormValidator {

    public int passwordLength = 6;

    private Map<String, String> mapErrors;

    public FormValidator() { this.mapErrors = new HashMap<>(); }

    public String[] getErrors() { return this.mapErrors.values().toArray(new String[0]); }

    public boolean hasValidated() { return this.mapErrors.isEmpty(); }

    public void validName(String name, String errorKey) {
        try {
            if (name.length() == 0) {
                this.mapErrors.put(errorKey, errorKey + " is empty");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void validEmail(String email, String errorKey) {
        try {
            if (email.length() == 0) {
                this.mapErrors.put(errorKey, errorKey + " is empty");
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                this.mapErrors.put(errorKey, errorKey + " is not valid");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void validPassword(String password, String errorKey) {
        try {
            if (password.length() == 0) {
                this.mapErrors.put(errorKey, errorKey + " is empty");
            } else if (password.length() < passwordLength) {
                this.mapErrors.put(errorKey, errorKey + " is short");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void validConfirmPassword(String password, String confirmPassword, String errorKey) {
        try {
            if (!password.equals(confirmPassword)) {
                this.mapErrors.put(errorKey, " password is not confirmed");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void validCheckBox(boolean checked, String errorKey) {
        if (!checked) {
            this.mapErrors.put(errorKey, errorKey + " is not checked");
        }
    }
}
