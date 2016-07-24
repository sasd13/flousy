package com.sasd13.flousy.bean;

import android.support.annotation.StringRes;

import com.sasd13.flousy.R;

/**
 * Created by ssaidali2 on 04/07/2016.
 */
public enum EnumOperationType {
    DEBIT("DEBIT", R.string.operation_type_debit),
    CREDIT("CREDIT", R.string.operation_type_credit),
    ;

    private String code;
    private int stringRes;

    private EnumOperationType(String code, @StringRes int stringRes) {
        this.code = code;
        this.stringRes = stringRes;
    }

    public String getCode() {
        return code;
    }

    public int getStringRes() {
        return stringRes;
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
