package flousy.beans.core;

import flousy.beans.Person;

/**
 * Created by Samir on 19/06/2015.
 */
public class User extends Person {

    private long id;
    private String email, password;

    public User() {
        super();
    }

    public User(String firstName, String lastName, String email, String password) {
        super(firstName, lastName);

        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return "["
                + "person: " + super.toString() + ", "
                + "id: " + this.id + ", "
                + "email: " + this.email + ", "
                + "password: " + this.password + "]";
    }
}
