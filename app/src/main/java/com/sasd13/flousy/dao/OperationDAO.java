package com.sasd13.flousy.dao;

import com.sasd13.flousy.bean.Operation;
import com.sasd13.javaex.db.IEntityDAO;

public interface OperationDAO extends IEntityDAO<Operation> {

    String TABLE = "operations";

    String COLUMN_ID = "id";
    String COLUMN_DATEREALIZATION = "daterealization";
    String COLUMN_TITLE = "title";
    String COLUMN_AMOUNT = "amount";
    String COLUMN_TYPE = "type";
    String COLUMN_ACCOUNT_ID = "account_id";
}