package com.sasd13.flousy.db.dao;

import com.sasd13.flousy.bean.Operation;
import com.sasd13.javaex.dao.ISession;

public interface IOperationDAO extends ISession<Operation> {

    String TABLE = "operations";
    String COLUMN_ID = "_id";
    String COLUMN_DATEREALIZATION = "_daterealization";
    String COLUMN_TITLE = "_title";
    String COLUMN_AMOUNT = "_amount";
    String COLUMN_TYPE = "_type";
    String COLUMN_ACCOUNT_ID = "_account_id";
}