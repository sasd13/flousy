package flousy.data.dao.accessor;

/**
 * Created by Samir on 11/06/2015.
 */
public interface DataAccessor extends ClientAccessor, PhoneAccessor, IncomeAccessor, PaymentsAccountAccessor, PaymentAccessor, SpendsAccountAccessor, SpendAccessor, ArticleAccessor {

    void open();
    void close();
    DataAccessorType getType();
}
