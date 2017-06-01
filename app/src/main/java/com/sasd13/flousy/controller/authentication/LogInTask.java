package com.sasd13.flousy.controller.authentication;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.flousy.bean.user.User;
import com.sasd13.flousy.service.IAuthenticationService;
import com.sasd13.flousy.service.ServiceResult;
import com.sasd13.javaex.security.Credential;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class LogInTask extends RequestorTask {

    private LogInController controller;
    private IAuthenticationService authenticationService;

    public LogInTask(LogInController controller, IAuthenticationService authenticationService) {
        this.controller = controller;
        this.authenticationService = authenticationService;
    }

    @Override
    public Object execute(Object in) {
        return authenticationService.logIn((Credential) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<User> result = (ServiceResult<User>) out;

        if (result.isSuccess()) {
            controller.onLogIn(result.getData());
        } else {
            controller.onFail(result.getErrors());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.onCancelled();
    }
}
