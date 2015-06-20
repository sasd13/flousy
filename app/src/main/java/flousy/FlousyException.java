package flousy;

/**
 * Created by Samir on 11/06/2015.
 */
public class FlousyException extends Exception {

    private String message;

    public FlousyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
