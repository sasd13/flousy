package com.sasd13.flousy.view;

import com.sasd13.flousy.bean.Customer;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public interface ICustomerController extends IController, IBrowsable {

    void actionUpdateCustomer(Customer customer);
}
