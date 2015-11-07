package flousy.bean;

import flousy.util.List;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListProducts extends List<Product> {

    public ListProducts() { super(); }

    @Override
    public Product get(Object id) {
        for (Product product : getList()) {
            if (product.getId() == (long) id) {
                return product;
            }
        }

        return null;
    }
}
