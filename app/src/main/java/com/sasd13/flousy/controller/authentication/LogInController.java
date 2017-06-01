package com.sasd13.flousy.controller.authentication;

import android.content.Intent;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.flousy.activity.IdentityActivity;
import com.sasd13.flousy.activity.MainActivity;
import com.sasd13.flousy.bean.user.User;
import com.sasd13.flousy.controller.IdentityController;
import com.sasd13.flousy.scope.Scope;
import com.sasd13.flousy.service.IAuthenticationService;
import com.sasd13.flousy.util.Constants;
import com.sasd13.flousy.view.ILogInController;
import com.sasd13.javaex.security.Credential;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class LogInController extends IdentityController implements ILogInController {

    private Scope scope;
    private IAuthenticationService authenticationService;
    private LogInTask logInTask;

    public LogInController(IdentityActivity identityActivity, IAuthenticationService authenticationService) {
        super(identityActivity);

        scope = new Scope();
        this.authenticationService = authenticationService;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void actionLogIn(String username, String password) {
        logIn(new Credential(username, password));
    }

    private void logIn(Credential credential) {
        scope.setLoading(true);

        if (logInTask == null) {
            logInTask = new LogInTask(this, authenticationService);
        }

        new Requestor(logInTask).execute(credential);
    }

    void onLogIn(User user) {
        scope.setLoading(false);
        goToMainActivity(user);
    }

    private void goToMainActivity(User user) {
        Intent intent = new Intent(getActivity(), MainActivity.class);

        intent.putExtra(Constants.USER, user);
        intent.putExtra(Constants.WELCOME, "WELCOME");

        startActivity(intent);
    }
}
