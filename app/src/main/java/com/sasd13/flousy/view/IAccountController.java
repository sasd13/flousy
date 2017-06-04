package com.sasd13.flousy.view;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public interface IAccountController extends IController, IBrowsable {

    void actionNewAccount();

    void actionCreateAccount(Account account);

    void actionShowAccount(Account account);

    void actionUpdateAccount(Account account);
}
