package com.sasd13.flousy.dao;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.util.EnumParameter;
import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDeepReader extends DeepReader<Account> {

    private CustomerDAO customerDAO;
    private OperationDAO operationDAO;

    public AccountDeepReader(IEntityDAO<Account> entityDAO, CustomerDAO customerDAO, OperationDAO operationDAO) {
        super(entityDAO);

        this.customerDAO = customerDAO;
        this.operationDAO = operationDAO;
    }

    @Override
    protected void retrieveData(Account account) throws DAOException {
        Customer customer = customerDAO.select(account.getCustomer().getId());
        account.getCustomer().setFirstName(customer.getFirstName());
        account.getCustomer().setLastName(customer.getLastName());
        account.getCustomer().setEmail(customer.getEmail());

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(EnumParameter.ACCOUNT.getName(), new String[]{ String.valueOf(account.getId()) });

        List<Operation> operations = operationDAO.select(parameters);
        Operation operationToAdd;
        for (Operation operation : operations) {
            operationToAdd = new Operation(account);
            operationToAdd.setId(operation.getId());
            operationToAdd.setDateRealization(operation.getDateRealization());
            operationToAdd.setTitle(operation.getTitle());
            operationToAdd.setAmount(operation.getAmount());
        }
    }
}