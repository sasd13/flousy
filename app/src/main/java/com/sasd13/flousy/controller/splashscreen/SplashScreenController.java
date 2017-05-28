package com.sasd13.flousy.controller.splashscreen;

import android.content.Intent;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.flousy.activity.IdentityActivity;
import com.sasd13.flousy.activity.MainActivity;
import com.sasd13.flousy.activity.SplashScreenActivity;
import com.sasd13.flousy.bean.user.User;
import com.sasd13.flousy.controller.Controller;
import com.sasd13.flousy.scope.Scope;
import com.sasd13.flousy.service.IAuthenticationService;
import com.sasd13.flousy.service.ISessionStorageService;
import com.sasd13.flousy.service.IUserService;
import com.sasd13.flousy.util.Constants;
import com.sasd13.flousy.view.ISplashScreenController;

/**
 * Created by ssaidali2 on 08/05/2017.
 */

public class SplashScreenController extends Controller implements ISplashScreenController {

    private ISessionStorageService sessionStorageService;
    private IAuthenticationService authenticationService;
    private IUserService userService;
    private UserReadTask userReadTask;

    public SplashScreenController(SplashScreenActivity splashScreenActivity, ISessionStorageService sessionStorageService, IAuthenticationService authenticationService, IUserService userService) {
        super(splashScreenActivity);

        this.sessionStorageService = sessionStorageService;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @Override
    public Scope getScope() {
        return null;
    }

    @Override
    public void run() {
        if (!authenticationService.isAuthenticated()) {
            goToIdentityActivity();
        } else {
            readUser();
        }
    }

    private void goToIdentityActivity() {
        startActivity(new Intent(getActivity(), IdentityActivity.class));
    }

    private void readUser() {
        if (userReadTask == null) {
            userReadTask = new UserReadTask(this, userService);
        }

        new Requestor(userReadTask).execute(sessionStorageService.getUserID());
    }

    void onReadUser(User user) {
        goToMainActivity(user);
    }

    private void goToMainActivity(User user) {
        Intent intent = new Intent(getActivity(), MainActivity.class);

        intent.putExtra(Constants.USER, user);

        startActivity(intent);
    }
}
