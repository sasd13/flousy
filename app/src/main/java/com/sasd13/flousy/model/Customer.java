package com.sasd13.flousy.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

@Entity(generateGettersSetters = false)
public class Customer {

    @Property(nameInDb = "_id")
    @Id(autoincrement = true)
    private long id;

    @Property(nameInDb = "_accountID")
    @NotNull
    private String intermediary;

    @Property(nameInDb = "_firstname")
    @NotNull
    private String firstName;

    @Property(nameInDb = "_lastname")
    @NotNull
    private String lastName;

    @Property(nameInDb = "_email")
    @NotNull
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
}
