package flousy.db.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.util.Map;

import flousy.content.customer.Account;
import flousy.content.customer.Customer;
import flousy.content.finance.Payment;
import flousy.content.spend.Category;
import flousy.content.spend.Product;
import flousy.content.spend.Spend;
import flousy.db.DBAccessor;
import flousy.util.FlousyCollection;

public class SQLiteDAO implements DBAccessor {

    private static final int VERSION = 1;
    private static final String NOM = "database.db";

    private static SQLiteDAO instance = null;

	private SQLiteDatabase db = null;
	private SQLiteDBHandler dbHandler = null;

    private CustomerDAO customerDAO;
    private AccountDAO accountDAO;
    private PaymentDAO paymentDAO;
    private SpendDAO spendDAO;
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;

	private SQLiteDAO() {
        customerDAO = new CustomerDAO();
        accountDAO = new AccountDAO();
        paymentDAO = new PaymentDAO();
        spendDAO = new SpendDAO();
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
        paymentDAO.setDb(db);
        spendDAO.setDb(db);
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
    public long insert(Object object) {
        switch (object.getClass().getSimpleName()) {
            case "Customer":
                return customerDAO.insert((Customer) object);
            case "Category":
                return categoryDAO.insert((Category) object);
            default:
                return 0;
        }
    }

    @Override
    public long insert(Object object, Map<Class, Object> map) {
        switch (object.getClass().getSimpleName()) {
            case "Account":
                return accountDAO.insert((Account) object, (Customer) map.get(Customer.class));
            case "Payment":
                return paymentDAO.insert((Payment) object, (Account) map.get(Account.class));
            case "Spend":
                return spendDAO.insert((Spend) object, (Account) map.get(Account.class));
            case "Product":
                return productDAO.insert((Product) object, (Spend) map.get(Spend.class));
            default:
                return 0;
        }
    }

    @Override
    public void update(Object object) {
        switch (object.getClass().getSimpleName()) {
            case "Customer":
                customerDAO.update((Customer) object);
                break;
            case "Account":
                accountDAO.update((Account) object);
                break;
            case "Payment":
                paymentDAO.update((Payment) object);
                break;
            case "Spend":
                spendDAO.update((Spend) object);
                break;
            case "Category":
                categoryDAO.update((Category) object);
                break;
            case "Product":
                productDAO.update((Product) object);
                break;
        }
    }

    @Override
    public void delete(Object object) {
        switch (object.getClass().getSimpleName()) {
            case "Customer":
                customerDAO.delete((Customer) object);
                break;
            case "Account":
                accountDAO.delete((Account) object);
                break;
            case "Payment":
                paymentDAO.delete((Payment) object);
                break;
            case "Spend":
                spendDAO.delete((Spend) object);
                break;
            case "Category":
                categoryDAO.delete((Category) object);
                break;
            case "Product":
                productDAO.delete((Product) object);
                break;
        }
    }

    @Override
    public Object select(String classSimpleName, long id) {
        switch (classSimpleName) {
            case "Customer":
                return customerDAO.select(id);
            case "Payment":
                return paymentDAO.select(id);
            case "Spend":
                return spendDAO.select(id);
            case "Product":
                return productDAO.select(id);
            default:
                return null;
        }
    }

    @Override
    public FlousyCollection selectAll(String classSimpleName) {
        switch (classSimpleName) {
            case "Customer":
                return customerDAO.selectAll();
            case "Payment":
                return paymentDAO.selectAll();
            case "Spend":
                return spendDAO.selectAll();
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
