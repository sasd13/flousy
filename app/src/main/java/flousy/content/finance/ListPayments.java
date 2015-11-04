package flousy.content.finance;

import flousy.util.FlousyList;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListPayments extends FlousyList<Payment> {

    @Override
    public Payment get(long id) {
        for (Payment payment : this.list) {
            if (payment.getId() == id) {
                return payment;
            }
        }

        return null;
    }
}
