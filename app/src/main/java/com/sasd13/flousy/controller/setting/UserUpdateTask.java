package com.sasd13.flousy.controller.setting;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.flousy.bean.user.UserUpdate;
import com.sasd13.flousy.service.IUserService;
import com.sasd13.flousy.service.ServiceResult;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class UserUpdateTask extends RequestorTask {

    private SettingController controller;
    private IUserService service;

    public UserUpdateTask(SettingController controller, IUserService service) {
        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.update((UserUpdate) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<?> result = (ServiceResult<?>) out;

        if (result.isSuccess()) {
            controller.onUpdateUser();
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
