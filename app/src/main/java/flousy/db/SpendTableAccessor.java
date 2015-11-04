package flousy.db;

import flousy.content.spend.Spend;

/**
 * Created by Samir on 11/06/2015.
 */
public interface SpendTableAccessor extends TableAccessor<Spend> {

    String SPEND_TABLE_NAME = "spends";

    String SPEND_ID = "spend_id";
    String SPEND_DATE = "spend_date";
}
