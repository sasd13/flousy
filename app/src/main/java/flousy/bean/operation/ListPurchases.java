package flousy.bean.operation;

import flousy.bean.Product;
import flousy.util.ObservableList;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListPurchases extends ObservableList<Product> {

    @Override
    public Product get(Object id) {
        for (Product product : this.list) {
            if (product.getId() == (long) id) {
                return product;
            }
        }

        return null;
    }
}
