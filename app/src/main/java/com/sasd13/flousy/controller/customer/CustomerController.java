package com.sasd13.flousy.controller.customer;

import com.sasd13.flousy.activity.MainActivity;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.controller.Controller;
import com.sasd13.flousy.scope.CustomerScope;
import com.sasd13.flousy.scope.Scope;
import com.sasd13.flousy.service.ICustomerService;
import com.sasd13.flousy.view.ICustomerController;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public class CustomerController extends Controller implements ICustomerController {

    private CustomerScope scope;
    private ICustomerService customerService;

    public CustomerController(MainActivity mainActivity, ICustomerService customerService) {
        super(mainActivity);

        scope = new CustomerScope();
        this.customerService = customerService;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void browse() {
        readCustomer();
    }

    private void readCustomer() {

    }

    @Override
    public void actionUpdateCustomer(Customer customer) {

    }
}
