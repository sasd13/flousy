package com.sasd13.flousy.util;

/**
 * Created by ssaidali2 on 02/06/2017.
 */

public enum EnumPreferenceCategory {
    GENERAL("GEN");

    private String code;

    private EnumPreferenceCategory(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EnumPreferenceCategory find(String code) {
        for (EnumPreferenceCategory category : values()) {
            if (category.code.equalsIgnoreCase(code)) {
                return category;
            }
        }

        return null;
    }
}
