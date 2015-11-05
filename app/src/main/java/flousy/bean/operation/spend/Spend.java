package flousy.bean.operation.spend;

import java.util.Observable;
import java.util.Observer;

import flousy.bean.Product;
import flousy.bean.operation.Operation;

/**
 * Created by Samir on 19/06/2015.
 */
public class Spend extends Operation implements Observer {

    private ListPurchases listPurchases;

    public Spend() {
        super();

        this.listPurchases = new ListPurchases();

        this.listPurchases.addObserver(this);
    }

    @Override
    public String getOperationType() {
        return "SPEND";
    }

    public ListPurchases getListPurchases() {
        return this.listPurchases;
    }

    @Override
    public void update(Observable observable, Object data) {
        for (Product product : this.listPurchases) {
            setValue(getValue() + product.getPrice());
        }
    }
}
