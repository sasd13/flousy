package com.sasd13.flousy.bean;

/**
 * Created by ssaidali2 on 04/07/2016.
 */
public enum OperationType {
    DEBIT("DBT", "operation_type_debit"),
    CREDIT("CRD", "operation_type_credit");

    private String code, label;

    private OperationType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static OperationType find(String code) {
        for (OperationType operationType : values()) {
            if (operationType.getCode().equalsIgnoreCase(code)) {
                return operationType;
            }
        }

        return null;
    }
}
