package com.sasd13.flousy.controller.sign;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.flousy.service.ICustomerService;
import com.sasd13.flousy.service.IUserService;
import com.sasd13.flousy.service.ServiceResult;
import com.sasd13.flousy.util.wrapper.UserSignWrapper;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class SignTask extends RequestorTask {

    private SignController controller;
    private IUserService userService;
    private ICustomerService customerService;

    public SignTask(SignController controller, IUserService userService, ICustomerService customerService) {
        this.controller = controller;
        this.userService = userService;
        this.customerService = customerService;
    }

    @Override
    public Object execute(Object in) {
        ServiceResult result;

        UserSignWrapper wrapper = (UserSignWrapper) in;
        result = userService.create(wrapper.getUserCreate());

        if (result.isSuccess()) {
            result = customerService.create(wrapper.getCustomer());
        }

        return result;
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<?> result = (ServiceResult<?>) out;

        if (result.isSuccess()) {
            controller.onSign();
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
