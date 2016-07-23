package com.sasd13.flousy.util;

import com.sasd13.flousy.bean.Operation;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class Binder {

    public static void bind(Operation target, Operation source) {
        target.setDateRealization(source.getDateRealization());
        target.setTitle(source.getTitle());
        target.setAmount(source.getAmount());
        target.setType(source.getType());
    }
}
