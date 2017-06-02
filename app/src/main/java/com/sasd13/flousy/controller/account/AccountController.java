package com.sasd13.flousy.controller.account;

import com.sasd13.flousy.activity.MainActivity;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.controller.Controller;
import com.sasd13.flousy.scope.AccountScope;
import com.sasd13.flousy.scope.Scope;
import com.sasd13.flousy.service.IAccountService;
import com.sasd13.flousy.view.IAccountController;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public class AccountController extends Controller implements IAccountController {

    private AccountScope scope;
    private IAccountService accountService;

    public AccountController(MainActivity mainActivity, IAccountService accountService) {
        super(mainActivity);

        scope = new AccountScope();
        this.accountService = accountService;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void browse() {
        readAccounts();
    }

    private void readAccounts() {

    }

    @Override
    public void actionNewAccount() {

    }

    @Override
    public void actionCreateAccount(Account account) {

    }

    @Override
    public void actionShowAccount(Account account) {

    }

    @Override
    public void actionUpdateAccount(Account account) {

    }
}
