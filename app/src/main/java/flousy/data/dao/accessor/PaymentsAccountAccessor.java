package flousy.data.dao.accessor;

import flousy.content.finance.PaymentsAccount;

/**
 * Created by Samir on 11/06/2015.
 */
public interface PaymentsAccountAccessor {

    void insertPaymentsAccount(PaymentsAccount paymentsAccount, String clientId);

    void updatePaymentsAccount(PaymentsAccount paymentsAccount);

    void deletePaymentsAccount(PaymentsAccount paymentsAccount);

    PaymentsAccount selectPaymentsAccount(String paymentsAccountOrClientId);
}
