package com.sasd13.flousy.bean;

public class CustomerAccount {

    private Customer customer;
    private Account account;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("CustomerAccount [");
        builder.append("customer=" + getCustomer());
        builder.append(", account=" + getAccount());
        builder.append("]");

        return builder.toString();
    }
}
