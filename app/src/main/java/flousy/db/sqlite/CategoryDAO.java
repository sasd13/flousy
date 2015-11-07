package flousy.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import flousy.bean.Category;
import flousy.bean.ListCategories;
import flousy.db.CategoryTableAccessor;

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
    protected Category getCursorValues(Cursor cursor) {
        Category category = new Category();

        category.setId(cursor.getLong(cursor.getColumnIndex(CATEGORY_ID)));
        category.setName(cursor.getString(cursor.getColumnIndex(CATEGORY_NAME)));

        return category;
    }

    @Override
    public long insert(Category category) {
        return getDB().insert(CATEGORY_TABLE_NAME, null, getContentValues(category));
    }

    @Override
    public void update(Category category) {
        getDB().update(CATEGORY_TABLE_NAME, getContentValues(category), CATEGORY_ID + " = ?", new String[]{String.valueOf(category.getId())});
    }

    @Override
    public void delete(Category category) {
        getDB().delete(CATEGORY_TABLE_NAME, CATEGORY_ID + " = ?", new String[]{String.valueOf(category.getId())});
    }

    @Override
    public Category select(long id) {
        Category category = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + CATEGORY_TABLE_NAME
                        + " where " + CATEGORY_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            category = getCursorValues(cursor);
        }
        cursor.close();

        return category;
    }

    @Override
    public ListCategories selectAll() {
        ListCategories listCategories = new ListCategories();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + CATEGORY_TABLE_NAME, null);

        while (cursor.moveToNext()) {
            listCategories.add(getCursorValues(cursor));
        }
        cursor.close();

        return listCategories;
    }
}
