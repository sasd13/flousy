package com.sasd13.flousy.view;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public interface IOperationController extends IController {

    void actionNewOperation(Account account);

    void actionCreateOperation(Operation operation);

    void actionShowOperation(Operation operation);

    void actionRemoveOperation(Operation operation);
}
