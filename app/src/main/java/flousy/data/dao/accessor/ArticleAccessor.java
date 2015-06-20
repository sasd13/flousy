package flousy.data.dao.accessor;

import flousy.content.spend.Article;
import flousy.content.spend.ListArticles;

/**
 * Created by Samir on 11/06/2015.
 */
public interface ArticleAccessor {

    void insertArticle(Article article, String spendId);

    void updateArticle(Article article);

    void deleteArticle(Article article);

    Article selectArticle(String articleId);

    ListArticles selectArticlesOfSpend(String spendId);
}
