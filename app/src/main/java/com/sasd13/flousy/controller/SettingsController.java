package com.sasd13.flousy.controller;

import com.sasd13.flousy.activities.MainActivity;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.fragment.ISettingsController;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public class SettingsController extends Controller implements ISettingsController {

    public SettingsController(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void entry() {
        showCustomer();
    }

    @Override
    public void showCustomer(Customer customer) {

    }

    @Override
    public void updateCustomer(Customer customer) {

    }
}
