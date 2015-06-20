package flousy.data.dao.accessor;

import flousy.content.finance.ListPayments;
import flousy.content.finance.Payment;

/**
 * Created by Samir on 11/06/2015.
 */
public interface PaymentAccessor {

    void insertPayment(Payment payment, String paymentsAccountId);

    void updatePayment(Payment payment);

    void deletePayment(Payment payment);

    Payment selectPayment(String paymentId);

    ListPayments selectPaymentsOfPaymentsAccount(String paymentsAccountId);
}
