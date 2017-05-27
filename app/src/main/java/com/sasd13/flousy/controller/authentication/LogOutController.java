package com.sasd13.flousy.controller.authentication;

import com.sasd13.flousy.activity.MainActivity;
import com.sasd13.flousy.controller.MainController;
import com.sasd13.flousy.scope.Scope;
import com.sasd13.flousy.view.ILogOutController;

/**
 * Created by ssaidali2 on 05/12/2016.
 */
public class LogOutController extends MainController implements ILogOutController {

    public LogOutController(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public Scope getScope() {
        return null;
    }

    @Override
    public void logOut() {
        getActivity().exit();
    }
}
