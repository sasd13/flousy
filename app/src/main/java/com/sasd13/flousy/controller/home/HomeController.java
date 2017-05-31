package com.sasd13.flousy.controller.home;

import com.sasd13.flousy.activity.MainActivity;
import com.sasd13.flousy.controller.MainController;
import com.sasd13.flousy.scope.HomeScope;
import com.sasd13.flousy.scope.Scope;
import com.sasd13.flousy.util.Constants;
import com.sasd13.flousy.view.IHomeController;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public class HomeController extends MainController implements IHomeController {

    private HomeScope scope;

    public HomeController(MainActivity mainActivity) {
        super(mainActivity);

        scope = new HomeScope();
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void run() {
        if (getActivity().getIntent().hasExtra(Constants.WELCOME)) {
            showWelcome();
        }
    }

    private void showWelcome() {
        scope.setWelcomed(true);
        getActivity().getIntent().removeExtra(Constants.WELCOME);
        scope.setWelcomed(false);
    }
}
