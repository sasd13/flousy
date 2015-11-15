package flousy.bean;

import java.sql.Timestamp;

public class Transaction {

    private long id;
    private Timestamp dateRealization;
    private String title;
    private double value;

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

    public String toString() {
        return "["
                + "id: " + this.id + ", "
                + "daterealization: " + String.valueOf(this.dateRealization) + ", "
                + "title: " + this.title + ", "
                + "value: " + this.value + "]";
    }
}
