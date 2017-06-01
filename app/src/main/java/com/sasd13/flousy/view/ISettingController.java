package com.sasd13.flousy.view;

import com.sasd13.flousy.bean.user.UserUpdate;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface ISettingController extends IController, IBrowsable {

    void actionReadUser();

    void actionUpdateUser(UserUpdate userUpdate);
}
