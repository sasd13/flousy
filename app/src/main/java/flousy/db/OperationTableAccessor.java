package flousy.db;

import flousy.beans.core.Account;
import flousy.beans.core.ListOperations;
import flousy.beans.core.Operation;

/**
 * Created by Samir on 11/06/2015.
 */
public interface OperationTableAccessor extends TableAccessor<Operation> {

    String OPERATION_TABLE_NAME = "operations";

    String OPERATION_ID = "operation_id";
    String OPERATION_DATE = "operation_date";
    String OPERATION_TYPE = "operation_type";
    String OPERATION_NAME = "operation_name";
    String OPERATION_VALUE = "operation_value";
    String ACCOUNTS_ACCOUNT_ID = "accounts_account_id";

    long insert(Operation operation, Account account);

    ListOperations selectByAccount(long accountId);

    ListOperations selectByAccount(long accountId, boolean ascOrdered);
}
