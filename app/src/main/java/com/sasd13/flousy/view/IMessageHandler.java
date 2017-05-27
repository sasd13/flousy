package com.sasd13.flousy.view;

import android.support.annotation.StringRes;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface IMessageHandler {

    void display(@StringRes int resID);

    void display(String message);
}
