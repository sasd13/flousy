package com.sasd13.flousy.bean;

import java.sql.Timestamp;

public class Transaction {

    private long id;
    private Timestamp dateRealization;
    private String title;
    private double amount;
    private Account account;

    public Transaction() {
        dateRealization = new Timestamp(System.currentTimeMillis());
    }

    public Transaction(Account account) {
        this();

        this.account = account;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Transaction [");
        builder.append("id=" + getId());
        builder.append(", dateRealization=" + String.valueOf(getDateRealization()));
        builder.append(", title=" + getTitle());
        builder.append(", amount=" + getAmount());
        builder.append("]");

        return builder.toString();
    }
}
