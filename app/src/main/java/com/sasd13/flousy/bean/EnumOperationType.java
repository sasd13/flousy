package com.sasd13.flousy.bean;

/**
 * Created by ssaidali2 on 04/07/2016.
 */
public enum EnumOperationType {
    DEBIT("DEBIT"),
    CREDIT("CREDIT"),
    ;

    private String code;

    private EnumOperationType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EnumOperationType find(String code) {
        for (EnumOperationType type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }

        return null;
    }
}
