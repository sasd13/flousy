package com.sasd13.flousy.dao;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Transaction;
import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;

public class TransactionDeepReader extends DeepReader<Transaction> {

    private AccountDAO accountDAO;

    public TransactionDeepReader(IEntityDAO<Transaction> entityDAO, AccountDAO accountDAO) {
        super(entityDAO);

        this.accountDAO = accountDAO;
    }

    @Override
    protected void retrieveData(Transaction transaction) throws DAOException {
        Account account = accountDAO.select(transaction.getAccount().getId());
        transaction.getAccount().setDateOpening(account.getDateOpening());
    }
}