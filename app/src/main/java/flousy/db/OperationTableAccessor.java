package flousy.db;

import flousy.bean.trading.ITradingAccount;
import flousy.bean.trading.ITrafficOperation;
import flousy.bean.trading.ListTrafficOperations;

/**
 * Created by Samir on 11/06/2015.
 */
public interface OperationTableAccessor extends TableAccessor<ITrafficOperation> {

    String OPERATION_TABLE_NAME = "operations";

    String OPERATION_ID = "operation_id";
    String OPERATION_DATE = "operation_date";
    String OPERATION_TYPE = "operation_type";
    String OPERATION_NAME = "operation_name";
    String OPERATION_VALUE = "operation_value";
    String ACCOUNTS_ACCOUNT_ID = "accounts_account_id";

    long insert(ITrafficOperation trafficOperation, ITradingAccount tradingAccount);

    ListTrafficOperations selectByAccount(long accountId);

    ListTrafficOperations selectByAccount(long accountId, boolean ascOrdered);
}
