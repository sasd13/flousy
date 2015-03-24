package flousy.data;

import flousy.content.user.User;

/**
 * Created by Samir on 13/03/2015.
 */
public class Validator {

    private Validator() {}

    public static boolean validUser(User user) {
        ValidatorCode codeFirstName = validName(user.getFirstName());
        ValidatorCode codeLastName = validName(user.getLastName());
        ValidatorCode codePhoneNumber = validPhoneNumber(user.getPhoneNumber());
        ValidatorCode codeEmail = validEmail(user.getEmail());
        ValidatorCode codePassword = validPassword(user.getPassword());

        if(codeFirstName == ValidatorCode.ERROR_NAME
                || codeLastName == ValidatorCode.ERROR_NAME
                || codePhoneNumber == ValidatorCode.ERROR_PHONENUMBER
                || codeEmail == ValidatorCode.ERROR_EMAIL
                || codePassword == ValidatorCode.ERROR_PASSWORD) {
            return false;
        }

        return true;
    }

    public static ValidatorCode validName(String name) {
        if(name.length() == 0) {
            return ValidatorCode.ERROR_NAME;
        }

        return ValidatorCode.OK;
    }

    public static ValidatorCode validPhoneNumber(String phoneNumber) {
        if(phoneNumber.length() == 0) {
            return ValidatorCode.ERROR_PHONENUMBER;
        }

        return ValidatorCode.OK;
    }

    public static ValidatorCode validEmail(String email) {
        if(email.length() == 0 || email.contains("@") == false) {
            return ValidatorCode.ERROR_EMAIL;
        }

        return ValidatorCode.OK;
    }

    public static ValidatorCode validPassword(String password) {
        if(password.length() == 0) {
            return ValidatorCode.ERROR_PASSWORD;
        }

        return ValidatorCode.OK;
    }
}
