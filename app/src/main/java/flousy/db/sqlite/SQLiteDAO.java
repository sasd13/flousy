package flousy.db.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import flousy.bean.ListCategories;
import flousy.bean.ListProducts;
import flousy.bean.customer.Customer;
import flousy.bean.Category;
import flousy.bean.Product;
import flousy.bean.trading.ITradingAccount;
import flousy.bean.trading.ListTrafficOperations;
import flousy.bean.trading.ITrafficOperation;
import flousy.bean.trading.debit.Debit;
import flousy.db.DBAccessor;

public class SQLiteDAO implements DBAccessor {

    private static final int VERSION = 1;
    private static final String NOM = "database.db";

    private static SQLiteDAO instance = null;

	private SQLiteDatabase db = null;
	private SQLiteDBHandler dbHandler = null;

    private CustomerDAO customerDAO;
    private AccountDAO accountDAO;
    private OperationDAO operationDAO;
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;

	private SQLiteDAO() {
        customerDAO = new CustomerDAO();
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

        customerDAO.setDb(db);
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
    public long insertCustomer(Customer customer) {
        long id = customerDAO.insert(customer);

        if (id > 0) {
            customer.setId(id);
        }

        return id;
    }

    @Override
    public long insertAccount(ITradingAccount tradingAccount, Customer customer) {
        long id = accountDAO.insert(tradingAccount, customer);

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
    public void updateCustomer(Customer customer) {
        customerDAO.update(customer);
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
    public void deleteCustomer(Customer customer) {
        customerDAO.delete(customer);
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
    public Customer selectCustomer(long id) {
        return customerDAO.select(id);
    }

    @Override
    public ITradingAccount selectAccount(long id) {
        ITradingAccount tradingAccount = accountDAO.select(id);

        if (tradingAccount != null) {
            ListTrafficOperations listTrafficOperations = operationDAO.selectOperationsByAccount(id);

            for (ITrafficOperation trafficOperation : listTrafficOperations) {
                tradingAccount.getListTrafficOperations().add(trafficOperation);
            }
        }

        return tradingAccount;
    }

    @Override
    public ITrafficOperation selectOperation(long id) {
        ITrafficOperation trafficOperation = operationDAO.select(id);

        if (trafficOperation != null && "SPEND".equalsIgnoreCase(trafficOperation.getTrafficType())) {
            ListProducts listProducts = productDAO.selectProductsByOperation(id);

            for (Product product : listProducts) {
                ((Debit) trafficOperation).getListPurchases().add(product);
            }
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

        if (product != null) {
            Category category = categoryDAO.select(product.getCategory().getId());
            product.setCategory(category);
        }

        return product;
    }

    @Override
    public ListCategories selectAllCategories() {
        return categoryDAO.selectAll();
    }

    @Override
    public Customer selectCustomerByEmail(String email) {
        return customerDAO.selectByEmail(email);
    }
}
