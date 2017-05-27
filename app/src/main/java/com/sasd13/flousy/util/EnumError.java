package com.sasd13.flousy.util;

import android.support.annotation.StringRes;

import com.sasd13.flousy.R;

/**
 * Created by Samir on 22/05/2016.
 */
public enum EnumError {
    UNKNOWN(0, R.string.exception_unknown),;

    private int code;

    @StringRes
    private int resID;

    private EnumError(int code, int resID) {
        this.code = code;
        this.resID = resID;
    }

    public int getCode() {
        return code;
    }

    public int getResID() {
        return resID;
    }

    public static EnumError find(int code) {
        for (EnumError error : values()) {
            if (error.code == code) {
                return error;
            }
        }

        return null;
    }
}
