package flousy.db;

import flousy.bean.ListProducts;
import flousy.bean.Product;
import flousy.bean.trading.ITrafficOperation;

/**
 * Created by Samir on 11/06/2015.
 */
public interface ProductTableAccessor extends TableAccessor<Product> {

    String PRODUCT_TABLE_NAME = "products";

    String PRODUCT_ID = "product_id";
    String PRODUCT_NAME = "product_name";
    String PRODUCT_PRICE = "product_price";
    String CATEGORIES_CATEGORY_ID = "categories_category_id";
    String OPERATIONS_OPERATION_ID = "operations_operation_id";

    long insert(Product product, ITrafficOperation trafficOperation);

    ListProducts selectProductsByOperation(long operationId);
}
