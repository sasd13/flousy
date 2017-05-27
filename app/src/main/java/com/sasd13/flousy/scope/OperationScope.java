package com.sasd13.flousy.scope;

import com.sasd13.flousy.bean.Operation;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public class OperationScope extends Scope {

    private Operation operation;

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;

        setChanged();
        notifyObservers();
    }
}
