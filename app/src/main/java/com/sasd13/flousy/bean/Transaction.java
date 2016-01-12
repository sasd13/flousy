package com.sasd13.flousy.bean;

import java.sql.Timestamp;

public class Transaction {

    private long id;
    private Timestamp dateRealization;
    private String title;
    private double value;
    private Account account;

    public Transaction() {
        dateRealization = new Timestamp(System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDateRealization() {
        return dateRealization;
    }

    public void setDateRealization(Timestamp dateRealization) {
        this.dateRealization = dateRealization;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Transaction [");
        builder.append("id=" + getId());
        builder.append(", dateRealization=" + String.valueOf(getDateRealization()));
        builder.append(", title=" + getTitle());
        builder.append(", value=" + getValue());
        builder.append("]");

        return builder.toString().trim();
    }
}
