package com.sasd13.flousy.controller;

import com.sasd13.flousy.activities.MainActivity;
import com.sasd13.flousy.view.fragment.ILogOutController;

/**
 * Created by ssaidali2 on 05/12/2016.
 */
public class LogOutController extends Controller implements ILogOutController {

    public LogOutController(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void entry() {
        logOut();
    }

    @Override
    public void logOut() {
        mainActivity.logOut();
    }
}
