package flousy.db;

import flousy.content.spend.Product;

/**
 * Created by Samir on 11/06/2015.
 */
public interface ProductTableAccessor extends TableAccessor<Product> {

    String PRODUCT_TABLE_NAME = "products";

    String PRODUCT_ID = "product_id";
    String PRODUCT_NAME = "product_name";
    String PRODUCT_PRICE = "product_price";
    String PRODUCT_CATEGORY = "product_category";
}
