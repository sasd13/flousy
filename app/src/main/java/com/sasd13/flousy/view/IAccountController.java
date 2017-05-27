package com.sasd13.flousy.view;

import com.sasd13.flousy.bean.Operation;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public interface IAccountController extends IController {

    void listOperations();

    void newOperation();

    void createOperation(Operation operation);

    void showOperation(Operation operation);

    void updateOperation(Operation operation, Operation operationToUpdate);

    void deleteOperation(Operation operation);
}
