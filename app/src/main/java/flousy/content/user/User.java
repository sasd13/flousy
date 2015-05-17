package flousy.content.user;

import android.graphics.drawable.Drawable;

import flousy.content.Person;

/**
 * Created by Samir on 07/03/2015.
 */
public class User extends Person {
    private String email;
    private String password;
    private Drawable image;
    private boolean connceted;
   // private String login;
    public User() {
        super();
       // this.login=null;
        this.email = null;
        this.password = null;
        this.image = null;
    }

    public void setConnceted(boolean connceted) {
        this.connceted = connceted;
    }

    public boolean isConnceted() {
        return connceted;
    }

    public User(String firstName, String lastName ,String phoneNumber, String email, String password, Drawable image) {
        super(firstName, lastName, phoneNumber);

        this.email = email;
        this.password = password;
        this.image = image;
       // this.login=login;
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

    public Drawable getImage() {
        return this.image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
