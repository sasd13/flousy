package com.sasd13.flousy.controller.home;

import com.sasd13.flousy.activity.MainActivity;
import com.sasd13.flousy.controller.MainController;
import com.sasd13.flousy.scope.Scope;
import com.sasd13.flousy.view.IHomeController;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public class HomeController extends MainController implements IHomeController {

    protected HomeController(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public Scope getScope() {
        return null;
    }
}
