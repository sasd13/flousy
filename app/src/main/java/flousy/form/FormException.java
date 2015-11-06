package flousy.form;

/**
 * Created by Samir on 09/06/2015.
 */
public class FormException extends Exception {

    private String message;

    public FormException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
