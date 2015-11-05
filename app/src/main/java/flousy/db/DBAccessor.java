package flousy.db;

import android.content.Context;

import flousy.bean.Category;
import flousy.bean.ListCategories;
import flousy.bean.Product;
import flousy.bean.customer.Customer;
import flousy.bean.trading.ITradingAccount;
import flousy.bean.trading.TrafficOperation;

/**
 * Created by Samir on 05/11/2015.
 */
public interface DBAccessor {

    void init(Context context);

    void open();

    void close();

    String getDBType();

    long insertCustomer(Customer customer);

    long insertAccount(ITradingAccount tradingAccount, Customer customer);

    long insertOperation(TrafficOperation trafficOperation, ITradingAccount tradingAccount);

    long insertCategory(Category category);

    long insertProduct(Product product, TrafficOperation trafficOperation);

    void updateCustomer(Customer customer);

    void updateAccount(ITradingAccount tradingAccount);

    void updateOperation(TrafficOperation trafficOperation);

    void updateCategory(Category category);

    void updateProduct(Product product);

    void deleteCustomer(Customer customer);

    void deleteAccount(ITradingAccount tradingAccount);

    void deleteOperation(TrafficOperation trafficOperation);

    void deleteCategory(Category category);

    void deleteProduct(Product product);

    Customer selectCustomer(long id);

    ITradingAccount selectAccount(long id);

    TrafficOperation selectOperation(long id);

    Category selectCategory(long id);

    Product selectProduct(long id);

    ListCategories selectAllCategories();

    Customer selectCustomerByEmail(String email);
}
