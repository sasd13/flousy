package flousy.beans.core;

import flousy.util.ObservableList;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListOperations extends ObservableList<Operation> {

    public ListOperations() { super(); }

    @Override
    public Operation get(Object id) {
        for (Operation operation : getList()) {
            if (operation.getId() == (long) id) {
                return operation;
            }
        }

        return null;
    }
}
