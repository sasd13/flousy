package com.sasd13.flousy.view;

import com.sasd13.flousy.scope.Scope;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IController extends IMessageHandler {

    Scope getScope();
}
