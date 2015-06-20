package flousy.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import flousy.content.spend.Article;
import flousy.content.spend.Category;
import flousy.content.spend.ListArticles;

/**
 * Created by Samir on 02/04/2015.
 */
class ArticleDAO extends AbstractTableDAO {

    public static final String ARTICLE_TABLE_NAME = "articles";

    public static final String ARTICLE_ID = "article_id";
    public static final String ARTICLE_NAME = "article_name";
    public static final String ARTICLE_PRICE = "article_price";
    public static final String ARTICLE_CATEGORY = "article_category";
    public static final String ARTICLE_SPEND_ID = "article_spend_id";

    public long insert(Article article, String spendId) {
        ContentValues values = getContentValues(article);
        values.put(ARTICLE_SPEND_ID, spendId);

        return db.insert(ARTICLE_TABLE_NAME, null, values);
    }

    private ContentValues getContentValues(Article article) {
        ContentValues values = new ContentValues();

        values.put(ARTICLE_ID, article.getId());
        values.put(ARTICLE_NAME, article.getName());
        values.put(ARTICLE_PRICE, article.getPrice());
        values.put(ARTICLE_CATEGORY, article.getCategory().toString());

        return values;
    }

    public long update(Article article) {
        return db.update(ARTICLE_TABLE_NAME, getContentValues(article), ARTICLE_ID + " = ?", new String[]{article.getId()});
    }

    public long delete(Article article) {
        return db.delete(ARTICLE_TABLE_NAME, ARTICLE_ID + " = ?", new String[]{article.getId()});
    }

    public Article select(String articleId) {
        Article article = null;

        Cursor cursor = db.rawQuery(
                "select " + ARTICLE_NAME + ", " + ARTICLE_PRICE + ", " + ARTICLE_CATEGORY
                        + " from " + ARTICLE_TABLE_NAME
                        + " where " + ARTICLE_ID + " = ?", new String[]{articleId});

        if (cursor.moveToNext()) {
            article = new Article();
            article.setId(articleId);
            article.setName(cursor.getString(0));
            article.setPrice(cursor.getDouble(1));
            article.setCategory(Category.valueOf(cursor.getString(2)));
        }
        cursor.close();

        return article;
    }

    public ListArticles selectAllOfSpend(String spendId) {
        ListArticles listArticles = new ListArticles();

        Cursor cursor = db.rawQuery(
                "select " + ARTICLE_ID + ", " + ARTICLE_NAME + ", " + ARTICLE_PRICE + ", " + ARTICLE_CATEGORY
                        + " from " + ARTICLE_TABLE_NAME
                        + " where " + ARTICLE_SPEND_ID + " = ?", new String[]{spendId});

        Article article;

        while (cursor.moveToNext()) {
            article = new Article();
            article.setId(cursor.getString(0));
            article.setName(cursor.getString(1));
            article.setPrice(cursor.getDouble(2));
            article.setCategory(Category.valueOf(cursor.getString(3)));

            listArticles.add(article);
        }
        cursor.close();

        return listArticles;
    }

    public boolean contains(String articleId) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + ARTICLE_ID
                        + " from " + ARTICLE_TABLE_NAME
                        + " where " + ARTICLE_ID + " = ?", new String[]{articleId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
