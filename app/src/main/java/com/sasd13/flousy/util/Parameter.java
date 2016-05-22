package com.sasd13.flousy.util;

/**
 * Created by Samir on 22/05/2016.
 */
public enum Parameter {

    ACCOUNT("account"),
    CUSTOMER("customer"),
    DATEOPENING("dateopening"),
    DATEREALIZATION("daterealization"),
    EMAIL("email"),
    FIRSTNAME("firstname"),
    ID("id"),
    LASTNAME("lastname"),
    PASSWORD("password"),
    TITLE("title"),
    AMOUNT("amount");

    private String name;

    private Parameter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
