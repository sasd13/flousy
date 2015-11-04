package flousy.content.customer;

import flousy.util.FlousyCollection;

/**
 * Created by Samir on 04/11/2015.
 */
public class Account {

    protected long id;
    protected FlousyCollection listPayments;
    protected FlousyCollection listSpends;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Iterable getListPayments() {
        return this.listPayments;
    }

    public void setListPayments(FlousyCollection listPayments) {
        this.listPayments = listPayments;
    }

    public Iterable getListSpends() {
        return listSpends;
    }

    public void setListSpends(FlousyCollection listSpends) {
        this.listSpends = listSpends;
    }
}
