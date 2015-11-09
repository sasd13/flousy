package flousy.db.sqlite;

import android.content.Context;

import flousy.bean.ListCategories;
import flousy.bean.ListProducts;
import flousy.bean.user.User;
import flousy.bean.Category;
import flousy.bean.Product;
import flousy.bean.trading.ITradingAccount;
import flousy.bean.trading.ListTrafficOperations;
import flousy.bean.trading.ITrafficOperation;
import flousy.bean.trading.Debit;
import flousy.db.DataAccessor;

public class SQLiteDAO implements DataAccessor {

    private static final int VERSION = 1;
    private static final String NOM = "database.db";

    private static SQLiteDAO instance = null;

    private UserDAO userDAO;
    private AccountDAO accountDAO;
    private OperationDAO operationDAO;
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;

	private SQLiteDAO() {
        userDAO = new UserDAO();
        accountDAO = new AccountDAO();
        operationDAO = new OperationDAO();
        categoryDAO = new CategoryDAO();
        productDAO = new ProductDAO();
    }

    public static synchronized SQLiteDAO getInstance() {
        if (instance == null) {
            instance = new SQLiteDAO();
        }

        return instance;
    }

    @Override
    public String getDBType() {
        return "SQLITE";
    }

    @Override
    public void init(Context context) {
        SQLiteDBHandler dbHandler = new SQLiteDBHandler(context, NOM, null, VERSION);

        userDAO.setDBHandler(dbHandler);
        accountDAO.setDBHandler(dbHandler);
        operationDAO.setDBHandler(dbHandler);
        categoryDAO.setDBHandler(dbHandler);
        productDAO.setDBHandler(dbHandler);

        insertCategoriesIfNotExist();
    }

    protected void insertCategoriesIfNotExist() {
        categoryDAO.insert(new Category("Gastronomie"));
        categoryDAO.insert(new Category("Multimedia"));
        categoryDAO.insert(new Category("Mode"));
        categoryDAO.insert(new Category("Accessoire"));
        categoryDAO.insert(new Category("Loisir"));
        categoryDAO.insert(new Category("Culture"));
        categoryDAO.insert(new Category("Course"));
        categoryDAO.insert(new Category("Maison"));
        categoryDAO.insert(new Category("Sante"));
        categoryDAO.insert(new Category("Autre"));
    }

    @Override
    public long insertUser(User user) {
        long id = 0;

        userDAO.open();

        id = userDAO.insert(user);
        if (id > 0) {
            user.setId(id);
        }

        userDAO.close();

        return id;
    }

    @Override
    public long insertAccount(ITradingAccount tradingAccount, User user) {
        long id = 0;

        accountDAO.open();

        id = accountDAO.insert(tradingAccount, user);
        if (id > 0) {
            tradingAccount.setId(id);
        }

        accountDAO.close();

        return id;
    }

    @Override
    public long insertOperation(ITrafficOperation trafficOperation, ITradingAccount tradingAccount) {
        long id = 0;

        operationDAO.open();

        id = operationDAO.insert(trafficOperation, tradingAccount);
        if (id > 0) {
            trafficOperation.setId(id);
        }

        operationDAO.close();

        return id;
    }

    @Override
    public long insertProduct(Product product, ITrafficOperation trafficOperation) {
        long id = 0;

        productDAO.open();

        id = productDAO.insert(product, trafficOperation);
        if (id > 0) {
            product.setId(id);
        }

        productDAO.close();

        return id;
    }

    @Override
    public void updateUser(User user) {
        userDAO.open();

        userDAO.update(user);

        userDAO.close();
    }

    @Override
    public void updateAccount(ITradingAccount tradingAccount) {
        accountDAO.open();

        accountDAO.update(tradingAccount);

        accountDAO.close();
    }

    @Override
    public void updateOperation(ITrafficOperation trafficOperation) {
        operationDAO.open();

        operationDAO.update(trafficOperation);

        operationDAO.close();
    }

    @Override
    public void updateProduct(Product product) {
        productDAO.open();

        productDAO.update(product);

        productDAO.close();
    }

    @Override
    public void deleteUser(User user) {
        userDAO.open();

        userDAO.delete(user);

        userDAO.close();
    }

    @Override
    public void deleteAccount(ITradingAccount tradingAccount) {
        accountDAO.open();

        accountDAO.delete(tradingAccount);

        accountDAO.close();
    }

    @Override
    public void deleteOperation(ITrafficOperation trafficOperation) {
        operationDAO.open();

        operationDAO.delete(trafficOperation);

        operationDAO.close();
    }

    @Override
    public void deleteProduct(Product product) {
        productDAO.open();

        productDAO.delete(product);

        productDAO.close();
    }

    @Override
    public User selectUser(long id) {
        User user = null;

        userDAO.open();

        user = userDAO.select(id);

        userDAO.close();

        return user;
    }

    @Override
    public ITradingAccount selectAccount(long id) {
        ITradingAccount tradingAccount = null;

        accountDAO.open();

        tradingAccount = accountDAO.select(id);

        try {
            ListTrafficOperations listTrafficOperations = operationDAO.selectByAccount(tradingAccount.getId());

            for (ITrafficOperation trafficOperation : listTrafficOperations) {
                trafficOperation = selectOperation(trafficOperation.getId());
                tradingAccount.getListTrafficOperations().add(trafficOperation);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        accountDAO.close();

        return tradingAccount;
    }

    @Override
    public ITrafficOperation selectOperation(long id) {
        ITrafficOperation trafficOperation = null;

        operationDAO.open();

        trafficOperation = operationDAO.select(id);

        try {
            ListProducts listProducts = productDAO.selectByOperation(trafficOperation.getId());

            if (!listProducts.isEmpty()) {
                if ("DEBIT".equalsIgnoreCase(trafficOperation.getTrafficOperationType())) {
                    for (Product product : listProducts) {
                        product = selectProduct(product.getId());
                        ((Debit) trafficOperation).getListPurchases().add(product);
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        operationDAO.close();

        return trafficOperation;
    }

    @Override
    public Category selectCategory(long id) {
        Category category = null;

        categoryDAO.open();

        category = categoryDAO.select(id);

        categoryDAO.close();

        return category;
    }

    @Override
    public Product selectProduct(long id) {
        Product product = null;

        productDAO.open();

        product = productDAO.select(id);

        try {
            Category category = categoryDAO.select(product.getCategory().getId());
            product.setCategory(category);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        productDAO.close();

        return product;
    }

    @Override
    public User selectUserByEmail(String email) {
        User user = null;

        userDAO.open();

        user = userDAO.selectByEmail(email);

        userDAO.close();

        return user;
    }

    @Override
    public boolean containsUserByEmail(String email) {
        boolean contains = false;

        userDAO.open();

        contains = userDAO.contains(email);

        userDAO.close();

        return contains;
    }

    @Override
    public ITradingAccount selectAccountByUser(long userId) {
        ITradingAccount tradingAccount = null;

        accountDAO.open();

        tradingAccount = accountDAO.selectByUser(userId);

        try {
            tradingAccount = selectAccount(tradingAccount.getId());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        accountDAO.close();

        return tradingAccount;
    }

    @Override
    public ListTrafficOperations selectOperationsByAccount(long accountId) {
        ListTrafficOperations listTrafficOperations = null;

        operationDAO.open();

        listTrafficOperations = operationDAO.selectByAccount(accountId);

        for (ITrafficOperation trafficOperation : listTrafficOperations) {
            if ("DEBIT".equalsIgnoreCase(trafficOperation.getTrafficOperationType())) {
                ListProducts listProducts = productDAO.selectByOperation(trafficOperation.getId());

                for (Product product : listProducts) {
                    product = selectProduct(product.getId());
                    ((Debit) trafficOperation).getListPurchases().add(product);
                }
            }
        }

        return listTrafficOperations;
    }

    @Override
    public ListCategories selectAllCategories() {
        ListCategories listCategories = null;

        categoryDAO.open();

        listCategories = categoryDAO.selectAll();

        categoryDAO.close();

        return listCategories;
    }
}
