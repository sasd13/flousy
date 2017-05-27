package com.sasd13.flousy.bean;

public class Customer {

    private String intermediary, firstName, lastName, email;

    public String getIntermediary() {
        return intermediary;
    }

    public void setIntermediary(String intermediary) {
        this.intermediary = intermediary;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Customer [");
        builder.append("intermediary=" + getIntermediary());
        builder.append(", firstName=" + getFirstName());
        builder.append(", lastName=" + getLastName());
        builder.append(", email=" + getEmail());
        builder.append("]");

        return builder.toString();
    }
}
