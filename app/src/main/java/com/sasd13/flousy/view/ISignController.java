package com.sasd13.flousy.view;

import com.sasd13.flousy.util.wrapper.UserCreate;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public interface ISignController extends IController, IBrowsable {

    void actionSign(UserCreate userCreate, Customer customer);
}
