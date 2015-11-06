package flousy.gui.activitybar;

/**
 * Created by Samir on 20/06/2015.
 */
public class ActivityBarException extends Exception {

    private String message;

    public ActivityBarException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
