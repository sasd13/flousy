package com.sasd13.flousy.model;

import com.sasd13.flousy.util.EnumPreference;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by ssaidali2 on 08/05/2017.
 */

@Entity(generateGettersSetters = false)
public class UserPreference {

    @Property(nameInDb = "_id")
    @Id(autoincrement = true)
    private long id;

    @Property(nameInDb = "_value")
    @NotNull
    private String value;

    @ToOne(joinProperty = "userId")
    private User user;
    private long userId;

    @ToOne(joinProperty = "preferenceId")
    private Preference preference;
    private long preferenceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }

    public boolean matches(EnumPreference mate) {
        return preference.getCategory().equalsIgnoreCase(mate.getCategory()) && preference.getName().equalsIgnoreCase(mate.getName());
    }
}
