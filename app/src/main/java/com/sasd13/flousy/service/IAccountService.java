package com.sasd13.flousy.service;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;

import java.util.List;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public interface IAccountService {

    ServiceResult<Void> create(Account account);

    ServiceResult<Void> update(Account account);

    ServiceResult<Account> read(String accountID);

    ServiceResult<List<Account>> readAll(String intermediary);

    ServiceResult<Void> createOperation(Operation operation);

    ServiceResult<Void> updateOperation(Operation operation);

    ServiceResult<Void> deleteOperation(Operation operation);
}