package com.sasd13.flousy.dao;

import com.sasd13.flousy.bean.Operation;

import java.util.List;

public interface IOperationDAO {

    String TABLE = "operations";
    String COLUMN_ID = "_id";
    String COLUMN_CODE = "_code";
    String COLUMN_TYPE = "_type";
    String COLUMN_AMOUNT = "_amount";
    String COLUMN_DATEREALIZATION = "_daterealization";
    String COLUMN_TITLE = "_title";
    String COLUMN_ACCOUNT = "_account";
    String COLUMN_FLAG_DELETED = "_flag_deleted";

    long create(Operation operation);

    void update(Operation operation);

    void delete(Operation operation);

    List<Operation> read(String accountID);
}