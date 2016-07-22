package com.sasd13.flousy.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private long id;
    private Timestamp dateOpening;
    private List<Operation> operations;
    private Customer customer;

    public Account() {
        dateOpening = new Timestamp(System.currentTimeMillis());
        operations = new ArrayList<>();
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

    public Operation[] getOperations() {
        return operations.toArray(new Operation[operations.size()]);
    }

    boolean addOperation(Operation operation) {
        return !operations.contains(operation) && operations.add(operation);
    }

    public boolean removeOperation(Operation operation) {
        boolean removed = operations.contains(operation) && operations.remove(operation);

        if (removed) {
            operation.setAccount(null);
        }

        return removed;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getSold() {
        double sold = 0;

        for (Operation operation : operations) {
            sold += operation.getAmount();
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
