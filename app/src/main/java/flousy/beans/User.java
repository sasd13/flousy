package flousy.beans;

public class User {

    private String firstName, lastName, email, password;
    private Account account;

    public User() { this.account = new Account(); }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getAccount() {
        return this.account;
    }

    @Override
    public String toString() {
        return "["
                + "firstName: " + this.firstName + ", "
                + "lastName: " + this.lastName + ", "
                + "email: " + this.email + ", "
                + "password: " + this.password + ", "
                + "account: " + this.account + "]";
    }
}
