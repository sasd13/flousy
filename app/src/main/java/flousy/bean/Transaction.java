package flousy.bean;

import java.sql.Timestamp;

public class Transaction {

    private long id;
    private Timestamp dateRealization;
    private String title;
    private double value;
    private Account account;

    public Transaction() {
        this.dateRealization = new Timestamp(System.currentTimeMillis());
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDateRealization() {
        return this.dateRealization;
    }

    public void setDateRealization(Timestamp dateRealization) {
        this.dateRealization = dateRealization;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String toString() {
        return "["
                + "id: " + this.id + ", "
                + "daterealization: " + String.valueOf(this.dateRealization) + ", "
                + "value: " + this.value + ", "
                + "title: " + this.title + "]";
    }
}
