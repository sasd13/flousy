package flousy.db;

import flousy.content.customer.Account;
import flousy.content.spend.Spend;

/**
 * Created by Samir on 11/06/2015.
 */
public interface SpendTableAccessor extends TableAccessor<Spend> {

    String SPEND_TABLE_NAME = "spends";

    String SPEND_ID = "spend_id";
    String SPEND_DATE = "spend_date";
    String SPEND_VALUE = "spend_value";
    String ACCOUNTS_ACCOUNT_ID = "accounts_account_id";

    long insert(Spend spend, Account account);
}
