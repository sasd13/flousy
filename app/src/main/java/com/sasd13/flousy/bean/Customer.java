package com.sasd13.flousy.bean;

public class Customer {

    private long id;
    private String firstName, lastName, email, password;
    private Account account;

    public Customer() {
        account = new Account();

        account.setCustomer(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Customer [");
        builder.append("id=" + getId());
        builder.append(", firstName=" + getFirstName());
        builder.append(", lastName=" + getLastName());
        builder.append(", email=" + getEmail());
        builder.append(", password=" + getPassword());
        builder.append(", account=" + getAccount());
        builder.append("]");

        return builder.toString().trim();
    }
}
