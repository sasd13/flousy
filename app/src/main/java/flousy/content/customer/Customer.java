package flousy.content.customer;

import flousy.content.Person;
import flousy.content.Phone;

/**
 * Created by Samir on 19/06/2015.
 */
public class Customer extends Person {

    private long id;
    private String email;
    private String password;
    private Phone phone;
    private CustomerAccount account;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Phone getPhone() {
        return this.phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public CustomerAccount getAccount() {
        return this.account;
    }

    public void setAccount(CustomerAccount account) {
        this.account = account;
    }
}
