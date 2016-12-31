package com.sasd13.flousy.db.dao;

import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.util.EnumParameter;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.ISession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDeepReader extends DeepReader<Account> {

    private IOperationDAO operationDAO;
    private Map<String, String[]> parameters;

    public AccountDeepReader(IAccountDAO accountDAO, IOperationDAO operationDAO) {
        super(accountDAO);

        this.operationDAO = operationDAO;
        parameters = new HashMap<>();
    }

    @Override
    protected void retrieveData(Account account) {
        parameters.clear();
        parameters.put(EnumParameter.ACCOUNT.getName(), new String[]{ String.valueOf(account.getId()) });

        List<Operation> operations = operationDAO.select(parameters);
        Operation operationToAdd;
        for (Operation operation : operations) {
            operationToAdd = new Operation();
            operationToAdd.setDateRealization(operation.getDateRealization());
            operationToAdd.setTitle(operation.getTitle());
            operationToAdd.setAmount(operation.getAmount());
            operationToAdd.setType(operation.getType());
        }
    }
}