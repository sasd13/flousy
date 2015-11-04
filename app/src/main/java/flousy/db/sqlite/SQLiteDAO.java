package flousy.db.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import flousy.content.customer.Customer;
import flousy.content.finance.Payment;
import flousy.content.spend.Product;
import flousy.content.spend.Spend;
import flousy.db.DBAccessor;
import flousy.db.DBException;
import flousy.util.FlousyCollection;

public class SQLiteDAO implements DBAccessor {

    private static final int VERSION = 1;
    private static final String NOM = "database.db";

    private static SQLiteDAO instance = null;

	private SQLiteDatabase db = null;
	private SQLiteDBHandler dbHandler = null;

    private CustomerDAO customerDAO;
    private PaymentDAO paymentDAO;
    private SpendDAO spendDAO;
    private ProductDAO productDAO;

	private SQLiteDAO() {
        customerDAO = new CustomerDAO();
        paymentDAO = new PaymentDAO();
        spendDAO = new SpendDAO();
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
        paymentDAO.setDb(db);
        spendDAO.setDb(db);
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

    public long insert(Object object) throws DBException {
        switch (object.getClass().getSimpleName()) {
            case "Customer":
                return customerDAO.insert((Customer) object);
            case "Payment":
                return paymentDAO.insert((Payment) object);
            case "Spend":
                return spendDAO.insert((Spend) object);
            case "Product":
                return productDAO.insert((Product) object);
            default:
                throw new DBException("insert failed : class " + object.getClass().getSimpleName());
        }
    }

    public void update(Object object) throws DBException {
        switch (object.getClass().getSimpleName()) {
            case "Customer":
                customerDAO.update((Customer) object);
                break;
            case "Payment":
                paymentDAO.update((Payment) object);
                break;
            case "Spend":
                spendDAO.update((Spend) object);
                break;
            case "Product":
                productDAO.update((Product) object);
                break;
            default:
                throw new DBException("update failed : class " + object.getClass().getSimpleName());
        }
    }

    public void delete(Object object) throws DBException {
        switch (object.getClass().getSimpleName()) {
            case "Customer":
                customerDAO.update((Customer) object);
                break;
            case "Payment":
                paymentDAO.update((Payment) object);
                break;
            case "Spend":
                spendDAO.update((Spend) object);
                break;
            case "Product":
                productDAO.update((Product) object);
                break;
            default:
                throw new DBException("delete failed : class " + object.getClass().getSimpleName());
        }
    }

    public Object select(String classSimpleName, long id) throws DBException {
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
                throw new DBException("select failed : class " + classSimpleName + ", id : " + id);
        }
    }

    public FlousyCollection selectAll(String classSimpleName) throws DBException {
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
                throw new DBException("select all failed : class " + classSimpleName);
        }
    }
}
