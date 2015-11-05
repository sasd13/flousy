package flousy.db.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import flousy.bean.ListCategories;
import flousy.bean.ListProducts;
import flousy.bean.customer.Customer;
import flousy.bean.Category;
import flousy.bean.Product;
import flousy.bean.customer.IAccount;
import flousy.bean.customer.ListCustomers;
import flousy.bean.operation.spend.Spend;
import flousy.bean.trading.ITradingAccount;
import flousy.bean.trading.ListTradingAccounts;
import flousy.bean.trading.ListTrafficOperations;
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
    public String getDBType() {
        return "SQLITE";
    }

    @Override
    public long insertCustomer(Customer customer) {
        return customerDAO.insert(customer);
    }

    @Override
    public long insertAccount(ITradingAccount tradingAccount, Customer customer) {
        return accountDAO.insert(tradingAccount, customer);
    }

    @Override
    public long insertOperation(TrafficOperation trafficOperation, ITradingAccount tradingAccount) {
        return operationDAO.insert(trafficOperation, tradingAccount);
    }

    @Override
    public long insertCategory(Category category) {
        return categoryDAO.insert(category);
    }

    @Override
    public long insertProduct(Product product, TrafficOperation trafficOperation) {
        return productDAO.insert(product, trafficOperation);
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
    public void updateOperation(TrafficOperation trafficOperation) {
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
    public void deleteOperation(TrafficOperation trafficOperation) {
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
        Customer customer = customerDAO.select(id);

        if (customer != null) {
            ITradingAccount tradingAccount = accountDAO.selectAccountByCustomer(id);

            customer.setAccount(tradingAccount);
        }

        return customer;
    }

    @Override
    public ITradingAccount selectAccount(long id) {
        ITradingAccount tradingAccount = accountDAO.select(id);

        if (tradingAccount != null) {
            ListTrafficOperations listTrafficOperations = operationDAO.selectOperationsByAccount(id);

            for (TrafficOperation trafficOperation : listTrafficOperations) {
                tradingAccount.getListTrafficOperations().add(trafficOperation);
            }
        }

        return tradingAccount;
    }

    @Override
    public TrafficOperation selectOperation(long id) {
        TrafficOperation trafficOperation = operationDAO.select(id);

        if (trafficOperation != null && trafficOperation.getTrafficType().equalsIgnoreCase("SPEND")) {
            ListProducts listProducts = productDAO.selectProductsByOperation(id);

            Spend spend = new Spend();
            trafficOperation.setOperation(spend);

            for (Product product : listProducts) {
                spend.getListPurchases().add(product);
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
        return productDAO.select(id);
    }

    @Override
    public ListCustomers selectAllCustomers() {
        return customerDAO.selectAll();
    }

    @Override
    public ListTradingAccounts selectAllAccounts() {
        return accountDAO.selectAll();
    }

    @Override
    public ListTrafficOperations selectAllOperations() {
        return operationDAO.selectAll();
    }

    @Override
    public ListCategories selectAllCategories() {
        return categoryDAO.selectAll();
    }

    @Override
    public ListProducts selectAllProducts() {
        return productDAO.selectAll();
    }

    @Override
    public Customer selectCustomerByEmail(String email) {
        return customerDAO.selectByEmail(email);
    }
}
