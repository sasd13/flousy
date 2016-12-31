package com.sasd13.flousy.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String number;
    private Timestamp dateOpening;
    private List<Operation> operations;

    public Account() {
        operations = new ArrayList<>();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Timestamp getDateOpening() {
        return dateOpening;
    }

    public void setDateOpening(Timestamp dateOpening) {
        this.dateOpening = dateOpening;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
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
        builder.append("number=" + getNumber());
        builder.append(", dateOpening=" + String.valueOf(getDateOpening()));
        builder.append("]");

        return builder.toString();
    }
}
