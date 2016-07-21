package com.sasd13.flousy.bean;

import java.sql.Timestamp;

public class Operation {

    private long id;
    private Timestamp dateRealization;
    private String title;
    private double amount;
    private EnumOperationType type;
    private Account account;

    public Operation() {
        dateRealization = new Timestamp(System.currentTimeMillis());
    }

    public Operation(Account account) {
        this();

        this.account = account;
        account.addOperation(this);
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

    public EnumOperationType getType() {
        return type;
    }

    public void setType(EnumOperationType type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Operation [");
        builder.append("id=" + getId());
        builder.append(", dateRealization=" + String.valueOf(getDateRealization()));
        builder.append(", title=" + getTitle());
        builder.append(", amount=" + getAmount());
        builder.append(", type=" + getType());
        builder.append("]");

        return builder.toString();
    }
}
