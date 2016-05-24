package com.sasd13.flousy.dao;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;

public class OperationDeepReader extends DeepReader<Operation> {

    private AccountDAO accountDAO;

    public OperationDeepReader(IEntityDAO<Operation> entityDAO, AccountDAO accountDAO) {
        super(entityDAO);

        this.accountDAO = accountDAO;
    }

    @Override
    protected void retrieveData(Operation operation) throws DAOException {
        Account account = accountDAO.select(operation.getAccount().getId());
        operation.getAccount().setDateOpening(account.getDateOpening());
    }
}