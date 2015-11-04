package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import flousy.content.spend.Category;
import flousy.content.spend.ListCategories;
import flousy.db.CategoryTableAccessor;
import flousy.util.FlousyCollection;

/**
 * Created by Samir on 02/04/2015.
 */
class CategoryDAO extends SQLiteTableDAO<Category> implements CategoryTableAccessor {

    @Override
    protected ContentValues getContentValues(Category category) {
        ContentValues values = new ContentValues();

        values.put(CATEGORY_ID, category.getId());
        values.put(CATEGORY_NAME, category.getName());

        return values;
    }

    @Override
    protected Category extractCursorValues(Cursor cursor) {
        Category category = new Category();

        category.setId(cursor.getLong(cursor.getColumnIndex(CATEGORY_ID)));
        category.setName(cursor.getString(cursor.getColumnIndex(CATEGORY_NAME)));

        return category;
    }

    @Override
    public long insert(Category category) {
        return db.insert(CATEGORY_TABLE_NAME, null, getContentValues(category));
    }

    @Override
    public void update(Category category) {
        db.update(CATEGORY_TABLE_NAME, getContentValues(category), CATEGORY_ID + " = ?", new String[]{String.valueOf(category.getId())});
    }

    @Override
    public void delete(Category category) {
        db.delete(CATEGORY_TABLE_NAME, CATEGORY_ID + " = ?", new String[]{String.valueOf(category.getId())});
    }

    @Override
    public Category select(long id) {
        Category category = null;

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + CATEGORY_TABLE_NAME
                        + " where " + CATEGORY_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            category = extractCursorValues(cursor);
        }
        cursor.close();

        return category;
    }

    @Override
    public FlousyCollection<Category> selectAll() {
        FlousyCollection<Category> collection = new ListCategories();

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + CATEGORY_TABLE_NAME, null);

        while (cursor.moveToNext()) {
            collection.add(extractCursorValues(cursor));
        }
        cursor.close();

        return collection;
    }
}
