package com.sasd13.flousy.view;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public interface IOperationController extends IController {

    void actionNewOperation(Account account);

    void actionCreateOperation(Operation operation);

    void actionShowOperation(Operation operation);

    void actionRemoveOperation(Operation operation);
}
