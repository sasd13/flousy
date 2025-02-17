package com.sasd13.flousy.util.builder;

import com.sasd13.javaex.pattern.builder.IBuilder;

import java.sql.Timestamp;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class DefaultOperationBuilder implements IBuilder<Operation> {

    @Override
    public Operation build() {
        Operation operation = new Operation();
        operation.setDateRealization(new Timestamp(System.currentTimeMillis()));

        return operation;
    }
}
