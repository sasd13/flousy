package com.sasd13.flousy.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

@Entity(generateGettersSetters = false)
public class User {

    @Property(nameInDb = "_id")
    @Id(autoincrement = true)
    private long id;

    @Property(nameInDb = "_uid")
    @Index(unique = true)
    private String uuID;

    @Property(nameInDb = "_username")
    @NotNull
    private String username;

    @Property(nameInDb = "_password")
    @NotNull
    private String password;

    @Property(nameInDb = "_intermediary")
    @NotNull
    private String intermediary;

    @Property(nameInDb = "_email")
    @NotNull
    private String email;

    @Transient
    private UserPreferences userPreferences;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuID() {
        return uuID;
    }

    public void setUuID(String uuID) {
        this.uuID = uuID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIntermediary() {
        return intermediary;
    }

    public void setIntermediary(String intermediary) {
        this.intermediary = intermediary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserPreferences getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;
    }
}
