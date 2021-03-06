package com.sasd13.flousy.util.sorter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class OperationSorter {

    public static void byDate(List<Operation> operations) {
        byDate(operations, true);
    }

    public static void byDate(List<Operation> operations, final boolean byDesc) {
        Collections.sort(operations, new Comparator<Operation>() {
            @Override
            public int compare(Operation operation1, Operation operation2) {
                if (byDesc) {
                    return operation1.getDateRealization().before(operation2.getDateRealization())
                            ? 1
                            : operation1.getDateRealization().equals(operation2.getDateRealization())
                            ? 0
                            : -1;
                } else {
                    return operation1.getDateRealization().after(operation2.getDateRealization())
                            ? 1
                            : operation1.getDateRealization().equals(operation2.getDateRealization())
                            ? 0
                            : -1;
                }
            }
        });
    }
}
