package com.sasd13.flousy.bean;

import com.sasd13.flousy.bean.*;

import java.util.Date;

public class Operation {

    private long id;
    private String operationID, title;
    private EnumOperationType type;
    private double amount;
    private Date dateRealization;
    private Account account;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOperationID() {
        return operationID;
    }

    public void setOperationID(String operationID) {
        this.operationID = operationID;
    }

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

    public Date getDateRealization() {
        return dateRealization;
    }

    public void setDateRealization(Date dateRealization) {
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

    public void setAccount(Account account) {
        this.account = account;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Operation [");
        builder.append("id=" + getId());
        builder.append(", operationID=" + getOperationID());
        builder.append(", type=" + getType());
        builder.append(", amount=" + getAmount());
        builder.append(", dateRealization=" + String.valueOf(getDateRealization()));
        builder.append(", title=" + getTitle());
        builder.append("]");

        return builder.toString();
    }
}
