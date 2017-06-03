package com.sasd13.flousy.bean;

import java.util.Date;

public class Account {

    private long id;
    private String accountID;
    private Date dateOpening;
    private Customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Date getDateOpening() {
        return dateOpening;
    }

    public void setDateOpening(Date dateOpening) {
        this.dateOpening = dateOpening;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Account [");
        builder.append("id=" + getId());
        builder.append(", accountID=" + getAccountID());
        builder.append(", dateOpening=" + String.valueOf(getDateOpening()));
        builder.append("]");

        return builder.toString();
    }
}
