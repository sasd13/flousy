package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import flousy.bean.Category;
import flousy.bean.ListProducts;
import flousy.bean.Product;
import flousy.bean.trading.TrafficOperation;
import flousy.db.ProductTableAccessor;

/**
 * Created by Samir on 02/04/2015.
 */
class ProductDAO extends SQLiteTableDAO<Product> implements ProductTableAccessor {

    @Override
    protected ContentValues getContentValues(Product product) {
        ContentValues values = new ContentValues();

        values.put(PRODUCT_ID, product.getId());
        values.put(PRODUCT_NAME, product.getName());
        values.put(PRODUCT_PRICE, product.getPrice());
        values.put(CATEGORIES_CATEGORY_ID, product.getCategory().getId());

        return values;
    }

    @Override
    protected Product extractCursorValues(Cursor cursor) {
        Product product = new Product();

        product.setId(cursor.getLong(cursor.getColumnIndex(PRODUCT_ID)));
        product.setName(cursor.getString(cursor.getColumnIndex(PRODUCT_NAME)));
        product.setPrice(cursor.getDouble(cursor.getColumnIndex(PRODUCT_PRICE)));

        Category category = new Category();
        category.setId(cursor.getLong(cursor.getColumnIndex(CATEGORIES_CATEGORY_ID)));
        product.setCategory(category);

        return product;
    }

    @Override
    public long insert(Product product) {
        return 0;
    }

    @Override
    public long insert(Product product, TrafficOperation trafficOperation) {
        ContentValues values = getContentValues(product);
        values.put(OPERATIONS_OPERATION_ID, trafficOperation.getId());

        return db.insert(PRODUCT_TABLE_NAME, null, values);
    }

    @Override
    public void update(Product product) {
        db.update(PRODUCT_TABLE_NAME, getContentValues(product), PRODUCT_ID + " = ?", new String[]{String.valueOf(product.getId())});
    }

    @Override
    public void delete(Product product) {
        db.delete(PRODUCT_TABLE_NAME, PRODUCT_ID + " = ?", new String[]{String.valueOf(product.getId())});
    }

    @Override
    public Product select(long id) {
        Product product = null;

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + PRODUCT_TABLE_NAME
                        + " where " + PRODUCT_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            product = extractCursorValues(cursor);
        }
        cursor.close();

        return product;
    }

    @Override
    public ListProducts selectAll() {
        ListProducts listProducts = new ListProducts();

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + PRODUCT_TABLE_NAME, null);

        while (cursor.moveToNext()) {
            listProducts.add(extractCursorValues(cursor));
        }
        cursor.close();

        return listProducts;
    }
}
