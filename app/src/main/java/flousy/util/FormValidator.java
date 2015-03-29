package flousy.util;

import android.util.Patterns;

import flousy.content.user.User;

/**
 * Created by Samir on 13/03/2015.
 */
public class FormValidator {

    private FormValidator() {}

    public static boolean validUser(User user) {
        FormValidatorCode codeFirstName = validName(user.getFirstName());
        FormValidatorCode codeLastName = validName(user.getLastName());
        FormValidatorCode codePhoneNumber = validPhoneNumber(user.getPhoneNumber());
        FormValidatorCode codeEmail = validEmail(user.getEmail());
        FormValidatorCode codePassword = validPassword(user.getPassword());

        if(codeFirstName == FormValidatorCode.ERROR_NAME
                || codeLastName == FormValidatorCode.ERROR_NAME
                || codePhoneNumber == FormValidatorCode.ERROR_PHONENUMBER
                || codeEmail == FormValidatorCode.ERROR_EMAIL
                || codePassword == FormValidatorCode.ERROR_PASSWORD) {
            return false;
        }

        return true;
    }

    public static FormValidatorCode validName(String name) {
        if(name.length() == 0) {
            return FormValidatorCode.ERROR_NAME;
        }

        return FormValidatorCode.OK;
    }

    public static FormValidatorCode validPhoneNumber(String phoneNumber) {
        if(phoneNumber.length() == 0 || Patterns.PHONE.matcher(phoneNumber).matches() == false) {
            return FormValidatorCode.ERROR_PHONENUMBER;
        }

        return FormValidatorCode.OK;
    }

    public static FormValidatorCode validEmail(String email) {
        if(email.length() == 0 || Patterns.EMAIL_ADDRESS.matcher(email).matches() == false) {
            return FormValidatorCode.ERROR_EMAIL;
        }

        return FormValidatorCode.OK;
    }

    public static FormValidatorCode validPassword(String password) {
        if(password.length() == 0) {
            return FormValidatorCode.ERROR_PASSWORD;
        }

        return FormValidatorCode.OK;
    }
}
