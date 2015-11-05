package flousy.db;

import flousy.bean.trading.ITradingAccount;
import flousy.bean.trading.ListTrafficOperations;
import flousy.bean.trading.TrafficOperation;

/**
 * Created by Samir on 11/06/2015.
 */
public interface OperationTableAccessor extends TableAccessor<TrafficOperation> {

    String OPERATION_TABLE_NAME = "operations";

    String OPERATION_ID = "operation_id";
    String OPERATION_DATE = "operation_date";
    String OPERATION_TRAFFICTYPE = "operation_traffictype";
    String OPERATION_NAME = "operation_name";
    String OPERATION_OPERATIONTYPE = "operation_operationtype";
    String OPERATION_VALUE = "operation_value";
    String ACCOUNTS_ACCOUNT_ID = "accounts_account_id";

    long insert(TrafficOperation trafficOperation, ITradingAccount tradingAccount);

    ListTrafficOperations selectOperationsByAccount(long accountId);
}
