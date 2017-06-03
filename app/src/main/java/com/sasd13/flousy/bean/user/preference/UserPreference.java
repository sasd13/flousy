package com.sasd13.flousy.bean.user.preference;

/**
 * Created by ssaidali2 on 08/05/2017.
 */

public class UserPreference {

    private long id, userId;
    private String value;
    private Preference preference;

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
