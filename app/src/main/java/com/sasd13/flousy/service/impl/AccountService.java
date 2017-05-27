package com.sasd13.flousy.service.impl;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.dao.IAccountDAO;
import com.sasd13.flousy.dao.IOperationDAO;
import com.sasd13.flousy.service.IAccountService;
import com.sasd13.flousy.service.ServiceResult;

import java.util.Collections;
import java.util.List;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public class AccountService implements IAccountService {

    private IAccountDAO accountDAO;
    private IOperationDAO operationDAO;

    public AccountService(IAccountDAO accountDAO, IOperationDAO operationDAO) {
        this.accountDAO = accountDAO;
        this.operationDAO = operationDAO;
    }

    @Override
    public ServiceResult<Void> create(Account account) {
        accountDAO.create(account);

        return ServiceResult.SUCCESS;
    }

    @Override
    public ServiceResult<Void> update(Account account) {
        accountDAO.update(account);

        return ServiceResult.SUCCESS;
    }

    @Override
    public ServiceResult<Account> read(String accountID) {
        Account account = accountDAO.read(accountID);

        return new ServiceResult<>(
                true,
                Collections.<String, String>emptyMap(),
                account
        );
    }

    @Override
    public ServiceResult<List<Account>> readAll(String intermediary) {
        List<Account> accounts = accountDAO.readAll(intermediary);

        return new ServiceResult<>(
                true,
                Collections.<String, String>emptyMap(),
                accounts
        );
    }

    @Override
    public ServiceResult<Void> createOperation(Operation operation) {
        operationDAO.create(operation);

        return ServiceResult.SUCCESS;
    }

    @Override
    public ServiceResult<Void> updateOperation(Operation operation) {
        operationDAO.update(operation);

        return ServiceResult.SUCCESS;
    }

    @Override
    public ServiceResult<Void> deleteOperation(Operation operation) {
        operationDAO.delete(operation);

        return ServiceResult.SUCCESS;
    }
}
