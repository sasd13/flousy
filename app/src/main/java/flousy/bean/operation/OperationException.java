package flousy.bean.operation;

/**
 * Created by Samir on 05/11/2015.
 */
public class OperationException extends Exception {

    private String message;

    public OperationException(String message) { this.message = message; }

    @Override
    public String getMessage() {
        return this.message;
    }
}
