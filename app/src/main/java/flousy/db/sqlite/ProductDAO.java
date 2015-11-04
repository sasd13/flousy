package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import flousy.content.spend.ListProducts;
import flousy.content.spend.Product;
import flousy.content.spend.Category;
import flousy.db.ProductTableAccessor;
import flousy.util.FlousyCollection;

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
        values.put(PRODUCT_CATEGORY, product.getCategory().toString());

        return values;
    }

    @Override
    protected Product extractCursorValues(Cursor cursor) {
        Product product = new Product();

        product.setId(cursor.getLong(cursor.getColumnIndex(PRODUCT_ID)));
        product.setName(cursor.getString(cursor.getColumnIndex(PRODUCT_NAME)));
        product.setPrice(cursor.getDouble(cursor.getColumnIndex(PRODUCT_PRICE)));
        product.setCategory(new Category(cursor.getString(cursor.getColumnIndex(PRODUCT_CATEGORY))));

        return product;
    }

    @Override
    public long insert(Product product) {
        return db.insert(PRODUCT_TABLE_NAME, null, getContentValues(product));
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
    public FlousyCollection<Product> selectAll() {
        FlousyCollection<Product> collection = new ListProducts();

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + PRODUCT_TABLE_NAME, null);

        while (cursor.moveToNext()) {
            collection.add(extractCursorValues(cursor));
        }
        cursor.close();

        return collection;
    }
}
