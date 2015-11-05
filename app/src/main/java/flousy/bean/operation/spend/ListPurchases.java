package flousy.bean.operation.spend;

import flousy.bean.Product;
import flousy.util.ObservableList;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListPurchases extends ObservableList<Product> {

    @Override
    public Product get(Object object) {
        for (Product product : this.list) {
            if (product.getId() == (long) object) {
                return product;
            }
        }

        return null;
    }
}
