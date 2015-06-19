package flousy.content.spend;

import java.util.Date;

/**
 * Created by Samir on 19/06/2015.
 */
public class Spend {

    private String id;
    private Date date;
    private ListArticles listArticles;

    public Spend() {}

    public Spend(Date date) {
        this.date = date;
        this.listArticles = new ListArticles();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ListArticles getListArticles() {
        return this.listArticles;
    }

    public void setListArticles(ListArticles listArticles) {
        this.listArticles = listArticles;
    }
}
