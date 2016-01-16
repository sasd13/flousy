package com.sasd13.flousy.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Account {

    private long id;
    private Timestamp dateOpening;
    private boolean closed;
    private List<Transaction> transactions;
    private Customer customer;

    public Account() {
        dateOpening = new Timestamp(System.currentTimeMillis());
        transactions = new ArrayList<>();
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

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public Transaction[] getTransactions() {
        return transactions.toArray(new Transaction[transactions.size()]);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getSold() {
        double sold = 0;

        for (Transaction transaction : transactions) {
            sold += transaction.getValue();
        }

        return sold;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Account [");
        builder.append("id=" + getId());
        builder.append(", dateOpening=" + String.valueOf(getDateOpening()));
        builder.append(", closed=" + String.valueOf(isClosed()));
        builder.append(", transactions=" + Arrays.toString(getTransactions()));
        builder.append("]");

        return builder.toString().trim();
    }
}
