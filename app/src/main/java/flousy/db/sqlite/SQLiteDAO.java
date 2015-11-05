package flousy.db.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.util.Map;

import flousy.bean.trading.CheckingTradingAccount;
import flousy.bean.customer.Customer;
import flousy.bean.operation.payment.Payment;
import flousy.bean.Category;
import flousy.bean.Product;
import flousy.bean.operation.spend.Spend;
import flousy.bean.trading.ITradingAccount;
import flousy.bean.trading.TrafficOperation;
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
    public String getType() {
        return "SQLITE";
    }

    @Override
    public long insert(Customer customer) {
        return customerDAO.insert(customer);
    }

    @Override
    public long insert(ITradingAccount tradingAccount, Customer customer) {
        return accountDAO.insert(tradingAccount, customer);
    }

    @Override
    public long insert(TrafficOperation trafficOperation, ITradingAccount tradingAccount) {
        return operationDAO.insert(trafficOperation, tradingAccount);
    }

    @Override
    public long insert(Category category) {
        return categoryDAO.insert(category);
    }

    @Override
    public long insert(Product product, TrafficOperation trafficOperation) {
        return productDAO.insert(product, trafficOperation);
    }

    @Override
    public void update(Customer customer) {
        customerDAO.update(customer);
    }

    @Override
    public void update(ITradingAccount tradingAccount) {
        accountDAO.update(tradingAccount);
    }

    @Override
    public void update(TrafficOperation trafficOperation) {
        operationDAO.update(trafficOperation);
    }

    @Override
    public void update(Category category) {
        categoryDAO.update(category);
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }

    @Override
    public void delete(Customer customer) {
        customerDAO.delete(customer);
    }

    @Override
    public void delete(ITradingAccount tradingAccount) {
        accountDAO.delete(tradingAccount);
    }

    @Override
    public void delete(TrafficOperation trafficOperation) {
        operationDAO.delete(trafficOperation);
    }

    @Override
    public void delete(Category category) {
        categoryDAO.delete(category);
    }

    @Override
    public void delete(Product product) {
        productDAO.delete(product);
    }

    @Override
    public Object select(Class className, long id) {
        if (className == Customer.class) {
            return customerDAO.select(id);
        } else if (className == Tr)
    }

    @Override
    public Iterable selectAll(Class className) {
        return null;
    }

    @Override
    public Object select(Class className, long id) {
        switch (classSimpleName) {
            case "Customer":
                return customerDAO.select(id);
            case "Product":
                return productDAO.select(id);
            default:
                return null;
        }
    }

    @Override
    public Iterable selectAll(String classSimpleName) {
        switch (classSimpleName) {
            case "Customer":
                return customerDAO.selectAll();
            case "Payment":
                return paymentDAO.selectAll();
            case "Spend":
                return operationDAO.selectAll();
            case "Product":
                return productDAO.selectAll();
            default:
                return null;
        }
    }

    @Override
    public Customer selectCustomerByEmail(String email) {
        return customerDAO.selectByEmail(email);
    }
}
