package com.sasd13.flousy.view.fragment;

import com.sasd13.flousy.bean.Customer;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public interface ISignController extends IController {

    void signIn(String email, String password);

    void showSignUp();

    void signUp(Customer customer);
}
