package com.sasd13.flousy.view;

import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.bean.user.UserCreate;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public interface ISignController extends IController, IBrowsable {

    void sign(UserCreate userCreate, Customer customer);
}
