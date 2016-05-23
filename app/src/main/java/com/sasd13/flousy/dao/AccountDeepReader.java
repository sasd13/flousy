package com.sasd13.flousy.dao;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.bean.Transaction;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDeepReader extends DeepReader<Account> {

    private CustomerDAO customerDAO;
    private TransactionDAO transactionDAO;

    public AccountDeepReader(IEntityDAO<Account> entityDAO, CustomerDAO customerDAO, TransactionDAO transactionDAO) {
        super(entityDAO);

        this.customerDAO = customerDAO;
        this.transactionDAO = transactionDAO;
    }

    @Override
    protected void retrieveData(Account account) throws DAOException {
        Customer customer = customerDAO.select(account.getCustomer().getId());
        account.getCustomer().setFirstName(customer.getFirstName());
        account.getCustomer().setLastName(customer.getLastName());
        account.getCustomer().setEmail(customer.getEmail());

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.ACCOUNT.getName(), new String[]{ String.valueOf(account.getId()) });

        List<Transaction> transactions = transactionDAO.select(parameters);
        Transaction transactionToAdd;
        for (Transaction transaction : transactions) {
            transactionToAdd = new Transaction(account);
            transactionToAdd.setId(transaction.getId());
            transactionToAdd.setDateRealization(transaction.getDateRealization());
            transactionToAdd.setTitle(transaction.getTitle());
            transactionToAdd.setAmount(transaction.getAmount());
        }
    }
}