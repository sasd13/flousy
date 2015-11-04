package flousy.db;

import flousy.content.spend.Category;
import flousy.content.spend.Product;
import flousy.content.spend.Spend;

/**
 * Created by Samir on 11/06/2015.
 */
public interface ProductTableAccessor extends TableAccessor<Product> {

    String PRODUCT_TABLE_NAME = "products";

    String PRODUCT_ID = "product_id";
    String PRODUCT_NAME = "product_name";
    String PRODUCT_PRICE = "product_price";
    String CATEGORIES_CATEGORY_ID = "categories_category_id";
    String SPENDS_SPEND_ID = "spends_spend_id";

    long insert(Product product, Spend spend);
}
