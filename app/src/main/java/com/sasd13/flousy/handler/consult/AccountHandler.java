package com.sasd13.flousy.handler.consult;

import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.dao.OperationDAO;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.fragment.consult.AccountFragment;
import com.sasd13.javaex.db.DAOException;

import java.util.List;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class AccountHandler {

    private SQLiteDAO dao;

    public AccountHandler(AccountFragment accountFragment) {
        dao = SQLiteDAO.create(accountFragment.getContext());
    }

    public void deleteOperations(List<Operation> operations) {
        dao.open();

        try {
            dao.beginTransaction();

            OperationDAO operationDAO = (OperationDAO) dao.getEntityDAO(Operation.class);

            for (Operation operation : operations) {
                operation.getAccount().removeOperation(operation);
                operationDAO.delete(operation);
            }

            dao.commit();
        } catch (DAOException e) {
            dao.rollback();
            e.printStackTrace();
        } finally {
            dao.endTransaction();
            dao.close();
        }
    }
}
