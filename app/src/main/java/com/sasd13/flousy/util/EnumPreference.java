package com.sasd13.flousy.util;

/**
 * Created by ssaidali2 on 02/06/2017.
 */

public enum EnumPreference {
    GENERAL_DATE("GEN", "DT"),
    AMMOUNT_DECIMALFORMAT("AMT", "DF");

    private String category, name;

    private EnumPreference(String category, String name) {
        this.category = category;
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }
}
