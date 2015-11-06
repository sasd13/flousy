package flousy.bean.trading;

import flousy.bean.operation.ListPurchases;
import flousy.bean.operation.Spend;

/**
 * Created by Samir on 06/11/2015.
 */
public class Debit extends TrafficOperation {

    public Debit() { super(new Spend());}

    public Debit(String name) {
        this();

        setName(name);
    }

    @Override
    public String getTrafficOperationType() {
        return "DEBIT";
    }

    public ListPurchases getListPurchases() { return ((Spend) getOperation()).getListPurchases(); }
}
