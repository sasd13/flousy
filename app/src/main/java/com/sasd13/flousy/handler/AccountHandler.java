package com.sasd13.flousy.handler;

import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.dao.OperationDAO;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.activities.fragment.account.AccountFragment;
import com.sasd13.javaex.db.DAOException;

import java.util.List;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class AccountHandler {

    private AccountFragment accountFragment;
    private SQLiteDAO dao;

    public AccountHandler(AccountFragment accountFragment) {
        this.accountFragment = accountFragment;
        dao = SQLiteDAO.create(accountFragment.getContext());
    }

    public void deleteOperations(List<Operation> operations) {
        boolean deleted = false;

        dao.open();

        try {
            dao.beginTransaction();
            performDelete(operations);
            dao.commit();
            deleted = true;
        } catch (DAOException e) {
            dao.rollback();
            e.printStackTrace();
        } finally {
            dao.endTransaction();
            dao.close();
        }

        if (deleted) {
            accountFragment.onDeleteSucceeded();
        } else {
            accountFragment.onError(R.string.account_error_delete_operations);
        }
    }

    private void performDelete(List<Operation> operations) throws DAOException {
        OperationDAO operationDAO = (OperationDAO) dao.getEntityDAO(Operation.class);

        for (Operation operation : operations) {
            operation.getAccount().removeOperation(operation);
            operationDAO.delete(operation);
        }
    }
}
