package flousy.db;

import android.content.Context;

import java.util.Map;

import flousy.content.customer.Customer;
import flousy.util.FlousyCollection;

/**
 * Created by Samir on 11/06/2015.
 */
public interface DBAccessor {

    void init(Context context);

    void open();

    void close();

    String getType();

    long insert(Object object);

    long insert(Object object, Map<Class, Object> map);

    void update(Object object);

    void delete(Object object);

    Object select(String classSimpleName, long id);

    FlousyCollection selectAll(String classSimpleName);

    Customer selectCustomerByEmail(String email);
}
