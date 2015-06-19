package flousy.content.spend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListArticles implements Iterable {

    private List<Article> list;

    public ListArticles() {
        this.list = new ArrayList<>();
    }

    public boolean add(Article article) {
        return this.list.add(article);
    }

    public boolean remove(Article article) {
        return this.list.remove(article);
    }

    public Article remove(int index) {
        return this.list.remove(index);
    }

    public Article get(int index) {
        return this.list.get(index);
    }

    public boolean contains(Article article) {
        for (Article article2 : this.list) {
            if (article2.equals(article)) {
                return true;
            }
        }

        return false;
    }

    public void clear() {
        this.list.clear();
    }

    public void size() {
        this.list.size();
    }

    @Override
    public Iterator iterator() {
        return this.list.iterator();
    }
}
