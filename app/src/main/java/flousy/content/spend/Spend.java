package flousy.content.spend;

import java.sql.Timestamp;

/**
 * Created by Samir on 19/06/2015.
 */
public class Spend {

    private long id;
    private Timestamp date;
    private ListProducts listProducts;

    public Spend() {
        this.listProducts = new ListProducts();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return this.date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public ListProducts getListProducts() {
        return this.listProducts;
    }

    public void setListProducts(ListProducts listProducts) {
        this.listProducts = listProducts;
    }
}
