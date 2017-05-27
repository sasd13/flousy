package com.sasd13.flousy.controller.splashscreen;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.flousy.bean.user.User;
import com.sasd13.flousy.service.IUserService;
import com.sasd13.flousy.service.ServiceResult;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class UserReadTask extends RequestorTask {

    private SplashScreenController controller;
    private IUserService service;

    public UserReadTask(SplashScreenController controller, IUserService service) {
        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.find((String) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<User> result = (ServiceResult<User>) out;

        if (result.isSuccess()) {
            controller.onReadUser(result.getData());
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
