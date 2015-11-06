package flousy.db.sqlite;

import android.database.sqlite.SQLiteDatabase;
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
import flousy.db.DBAccessor;

public class SQLiteDAO implements DBAccessor {

    private static final int VERSION = 1;
    private static final String NOM = "database.db";

    private static SQLiteDAO instance = null;

	private SQLiteDatabase db = null;
	private SQLiteDBHandler dbHandler = null;

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
    public void init(Context context) {
        dbHandler = new SQLiteDBHandler(context, NOM, null, VERSION);
    }

    @Override
    public void open() {
        db = dbHandler.getWritableDatabase();

        userDAO.setDb(db);
        accountDAO.setDb(db);
        operationDAO.setDb(db);
        categoryDAO.setDb(db);
        productDAO.setDb(db);
	}

    @Override
	public void close() {
        db.close();
	}

    @Override
    public String getDBType() {
        return "SQLITE";
    }

    @Override
    public long insertUser(User user) {
        long id = userDAO.insert(user);

        if (id > 0) {
            user.setId(id);
        }

        return id;
    }

    @Override
    public long insertAccount(ITradingAccount tradingAccount, User user) {
        long id = accountDAO.insert(tradingAccount, user);

        if (id > 0) {
            tradingAccount.setId(id);
        }

        return id;
    }

    @Override
    public long insertOperation(ITrafficOperation trafficOperation, ITradingAccount tradingAccount) {
        long id = operationDAO.insert(trafficOperation, tradingAccount);

        if (id > 0) {
            trafficOperation.setId(id);
        }

        return id;
    }

    @Override
    public long insertCategory(Category category) {
        long id = categoryDAO.insert(category);

        if (id > 0) {
            category.setId(id);
        }

        return id;
    }

    @Override
    public long insertProduct(Product product, ITrafficOperation trafficOperation) {
        long id = productDAO.insert(product, trafficOperation);

        if (id > 0) {
            product.setId(id);
        }

        return id;
    }

    @Override
    public void updateUser(User user) {
        userDAO.update(user);
    }

    @Override
    public void updateAccount(ITradingAccount tradingAccount) {
        accountDAO.update(tradingAccount);
    }

    @Override
    public void updateOperation(ITrafficOperation trafficOperation) {
        operationDAO.update(trafficOperation);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDAO.update(category);
    }

    @Override
    public void updateProduct(Product product) {
        productDAO.update(product);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.delete(user);
    }

    @Override
    public void deleteAccount(ITradingAccount tradingAccount) {
        accountDAO.delete(tradingAccount);
    }

    @Override
    public void deleteOperation(ITrafficOperation trafficOperation) {
        operationDAO.delete(trafficOperation);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryDAO.delete(category);
    }

    @Override
    public void deleteProduct(Product product) {
        productDAO.delete(product);
    }

    @Override
    public User selectUser(long id) {
        return userDAO.select(id);
    }

    @Override
    public ITradingAccount selectAccount(long id) {
        ITradingAccount tradingAccount = accountDAO.select(id);

        try {
            ListTrafficOperations listTrafficOperations = operationDAO.selectOperationsByAccount(tradingAccount.getId());

            for (ITrafficOperation trafficOperation : listTrafficOperations) {
                trafficOperation = selectOperation(trafficOperation.getId());
                tradingAccount.getListTrafficOperations().add(trafficOperation);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return tradingAccount;
    }

    @Override
    public ITrafficOperation selectOperation(long id) {
        ITrafficOperation trafficOperation = operationDAO.select(id);

        try {
            ListProducts listProducts = productDAO.selectProductsByOperation(trafficOperation.getId());

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

        return trafficOperation;
    }

    @Override
    public Category selectCategory(long id) {
        return categoryDAO.select(id);
    }

    @Override
    public Product selectProduct(long id) {
        Product product = productDAO.select(id);

        try {
            Category category = categoryDAO.select(product.getCategory().getId());
            product.setCategory(category);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public User selectUserByEmail(String email) {
        return userDAO.selectByEmail(email);
    }

    @Override
    public boolean containsUserByEmail(String email) {
        return userDAO.contains(email);
    }

    @Override
    public ITradingAccount selectAccountByUser(long userId) {
        ITradingAccount tradingAccount = accountDAO.selectAccountByUser(userId);

        try {
            tradingAccount = selectAccount(tradingAccount.getId());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return tradingAccount;
    }

    @Override
    public ListCategories selectAllCategories() {
        return categoryDAO.selectAll();
    }
}
