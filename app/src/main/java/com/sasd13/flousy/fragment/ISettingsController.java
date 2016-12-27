package com.sasd13.flousy.fragment;

import com.sasd13.flousy.bean.Customer;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public interface ISettingsController extends IController {

    void showCustomer(Customer customer);

    void updateCustomer(Customer customer);
}
