package flousy.bean.trading;

/**
 * Created by Samir on 05/11/2015.
 */
public class TradingException extends Exception {

    private String message;

    public TradingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
