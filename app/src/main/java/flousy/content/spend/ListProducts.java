package flousy.content.spend;

import flousy.util.FlousyList;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListProducts extends FlousyList<Product> {

    @Override
    public Product get(long id) {
        for (Product product : this.list) {
            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }
}
