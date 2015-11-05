package flousy.bean.trading;

/**
 * Created by Samir on 05/11/2015.
 */
public class TrafficFactory {

    private TrafficFactory() {}

    public static ITraffic create(String type) throws TradingException {
        if ("OPERATION".equalsIgnoreCase(type)) {
            return new TrafficOperation();
        } else {
            throw new TradingException("traffic type not allowed");
        }
    }
}
