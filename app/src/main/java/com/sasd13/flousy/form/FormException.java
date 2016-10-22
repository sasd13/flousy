package com.sasd13.flousy.form;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by ssaidali2 on 11/07/2016.
 */
public class FormException extends Exception {

    private @StringRes int resMessage;

    public FormException(Context context, @StringRes int resMessage) {
        super(context.getString(resMessage));

        this.resMessage = resMessage;
    }

    public @StringRes int getResMessage() {
        return resMessage;
    }
}
