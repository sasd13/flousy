package com.sasd13.flousy.bean;

import java.sql.Timestamp;

public class Operation {

    private EnumOperationType type;
    private double amount;
    private Timestamp dateRealization;
    private String title;
    private Account account;

    public EnumOperationType getType() {
        return type;
    }

    public void setType(EnumOperationType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public Account getAccount() {
        return account;
    }

    void setAccount(Account account) {
        this.account = account;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Operation [");
        builder.append("type=" + getType());
        builder.append(", amount=" + getAmount());
        builder.append(", dateRealization=" + String.valueOf(getDateRealization()));
        builder.append(", title=" + getTitle());
        builder.append("]");

        return builder.toString();
    }
}
