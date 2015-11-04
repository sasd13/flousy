package flousy.db;

import flousy.content.customer.Account;
import flousy.content.finance.Payment;

/**
 * Created by Samir on 11/06/2015.
 */
public interface PaymentTableAccessor extends TableAccessor<Payment> {

    String PAYMENT_TABLE_NAME = "payments";

    String PAYMENT_ID = "payment_id";
    String PAYMENT_DATE = "payment_date";
    String PAYMENT_VALUE = "payment_value";
    String ACCOUNTS_ACCOUNT_ID = "accounts_account_id";

    long insert(Payment payment, Account account);
}
