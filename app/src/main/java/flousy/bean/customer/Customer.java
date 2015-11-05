package flousy.bean.customer;

import flousy.bean.Person;
import flousy.bean.Phone;

/**
 * Created by Samir on 19/06/2015.
 */
public class Customer extends Person {

    private long id;
    private String email, password;
    private Phone phone;
    private IAccount account;

    public Customer() {
        super();
    }

    public Customer(String firstName, String lastName, String email, String password, IAccount account) {
        super(firstName, lastName);

        this.email = email;
        this.password = password;
        this.account = account;
    }

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

    public IAccount getAccount() {
        return this.account;
    }

    public void setAccount(IAccount account) { this.account = account; }

    @Override
    public String toString() {
        return "["
                + "person: " + super.toString() + ", "
                + "id: " + this.id + ", "
                + "email: " + this.email + ", "
                + "password: " + this.password + ", "
                + "phone: " + this.phone.toString() + ", "
                + "account: " + this.account.toString() + "]";
    }
}
