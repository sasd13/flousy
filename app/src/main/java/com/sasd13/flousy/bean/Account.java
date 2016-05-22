package com.sasd13.flousy.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private long id;
    private Timestamp dateOpening;
    private List<Transaction> transactions;
    private Customer customer;

    public Account() {
        dateOpening = new Timestamp(System.currentTimeMillis());
        transactions = new ArrayList<>();
    }

    public Account(Customer customer) {
        this();

        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDateOpening() {
        return dateOpening;
    }

    public void setDateOpening(Timestamp dateOpening) {
        this.dateOpening = dateOpening;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getSold() {
        double sold = 0;

        for (Transaction transaction : transactions) {
            sold += transaction.getAmount();
        }

        return sold;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Account [");
        builder.append("id=" + getId());
        builder.append(", dateOpening=" + String.valueOf(getDateOpening()));
        builder.append("]");

        return builder.toString();
    }
}
