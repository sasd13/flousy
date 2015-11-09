package flousy.db;

import android.content.Context;

import flousy.bean.Category;
import flousy.bean.ListCategories;
import flousy.bean.ListProducts;
import flousy.bean.Product;
import flousy.bean.trading.ListTrafficOperations;
import flousy.bean.user.User;
import flousy.bean.trading.ITradingAccount;
import flousy.bean.trading.ITrafficOperation;

/**
 * Created by Samir on 05/11/2015.
 */
public interface DataAccessor {

    String getDBType();

    void init(Context context);

    long insertUser(User user);

    long insertAccount(ITradingAccount tradingAccount, User user);

    long insertOperation(ITrafficOperation trafficOperation, ITradingAccount tradingAccount);

    long insertProduct(Product product, ITrafficOperation trafficOperation);

    void updateUser(User user);

    void updateAccount(ITradingAccount tradingAccount);

    void updateOperation(ITrafficOperation trafficOperation);

    void updateProduct(Product product);

    void deleteUser(User user);

    void deleteAccount(ITradingAccount tradingAccount);

    void deleteOperation(ITrafficOperation trafficOperation);

    void deleteProduct(Product product);

    User selectUser(long id);

    ITradingAccount selectAccount(long id);

    ITrafficOperation selectOperation(long id);

    Category selectCategory(long id);

    Product selectProduct(long id);

    User selectUserByEmail(String email);

    boolean containsUserByEmail(String email);

    ITradingAccount selectAccountByUser(long userId);

    ListTrafficOperations selectOperationsByAccount(long accountId);

    ListCategories selectAllCategories();
}
