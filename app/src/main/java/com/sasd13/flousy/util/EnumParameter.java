package com.sasd13.flousy.util;

/**
 * Created by Samir on 22/05/2016.
 */
public enum EnumParameter {
    ACCOUNT("account"),
    AMOUNT("amount"),
    CUSTOMER("customer"),
    DATEOPENING("dateopening"),
    DATEREALIZATION("daterealization"),
    EMAIL("email"),
    FIRSTNAME("firstname"),
    ID("id"),
    LASTNAME("lastname"),
    TITLE("title"),
    ;

    private String name;

    private EnumParameter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EnumParameter find(String name) {
        for (EnumParameter parameter : values()) {
            if (parameter.name.equalsIgnoreCase(name)) {
                return parameter;
            }
        }

        return null;
    }
}
