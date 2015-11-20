package flousy.bean;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private long id;
    private String firstName, lastName, email, password;
    private List<Account> accounts;

    public Customer() {
        this.accounts = new ArrayList<>();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public void addAccount(Account account) {
        this.accounts.add(account);

        account.setCustomer(this);
    }

    public void removeAccount(Account account) {
        this.accounts.remove(account);
    }

    public Account[] getAccounts() {
        return this.accounts.toArray(new Account[this.accounts.size()]);
    }

    @Override
    public String toString() {
        return "["
                + "id: " + this.id + ", "
                + "firstname: " + this.lastName + ", "
                + "lastname: " + this.lastName + ", "
                + "email: " + this.email + ", "
                + "password: " + this.password + "]";
    }
}
