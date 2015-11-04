package flousy.db;

/**
 * Created by Samir on 04/11/2015.
 */
public class DBException extends Exception {

    private String message;

    public DBException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
