package com.sasd13.flousy.db.dao;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.ISession;

public class OperationDeepReader extends DeepReader<Operation> {

    private IAccountDAO accountDAO;

    public OperationDeepReader(ISession<Operation> entityDAO, IAccountDAO accountDAO) {
        super(entityDAO);

        this.accountDAO = accountDAO;
    }

    @Override
    protected void retrieveData(Operation operation) {
        Account account = accountDAO.select(operation.getAccount().getId());
        operation.getAccount().setDateOpening(account.getDateOpening());
    }
}