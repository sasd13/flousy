package flousy.data.db.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import flousy.content.Client;
import flousy.content.Phone;
import flousy.content.finance.Income;
import flousy.content.finance.ListIncomes;
import flousy.content.finance.ListPayments;
import flousy.content.finance.Payment;
import flousy.content.finance.PaymentsAccount;
import flousy.content.spend.Article;
import flousy.content.spend.ListArticles;
import flousy.content.spend.ListSpends;
import flousy.content.spend.Spend;
import flousy.content.spend.SpendsAccount;
import flousy.data.dao.accessor.DataAccessor;
import flousy.data.dao.accessor.DataAccessorType;

public class SQLiteDAO implements DataAccessor {

    private static SQLiteDAO instance = null;
	
	private static final int VERSION = 1;
	private static final String NOM = "database.db";
	private SQLiteDatabase db = null;
	private DatabaseHandler dbHandler = null;

    private ClientDAO clientDAO;
	private PhoneDAO phoneDAO;
    private IncomeDAO incomeDAO;
    private PaymentsAccountDAO paymentsAccountDAO;
    private PaymentDAO paymentDAO;
    private SpendsAccountDAO spendsAccountDAO;
    private SpendDAO spendDAO;
    private ArticleDAO articleDAO;

	private SQLiteDAO() {
        clientDAO = new ClientDAO();
        phoneDAO = new PhoneDAO();
        incomeDAO = new IncomeDAO();
        paymentsAccountDAO = new PaymentsAccountDAO();
        paymentDAO = new PaymentDAO();
        spendsAccountDAO = new SpendsAccountDAO();
        spendDAO = new SpendDAO();
        articleDAO = new ArticleDAO();
    }

    public static synchronized SQLiteDAO getInstance() {
        if (instance == null) {
            instance = new SQLiteDAO();
        }

        return instance;
    }

    public void create(Context context) {
        dbHandler = new DatabaseHandler(context, NOM, null, VERSION);
    }

	@Override
    public void open() {
        db = dbHandler.getWritableDatabase();

        clientDAO.setDb(db);
        phoneDAO.setDb(db);
        incomeDAO.setDb(db);
        paymentsAccountDAO.setDb(db);
        paymentDAO.setDb(db);
        spendsAccountDAO.setDb(db);
        spendDAO.setDb(db);
        articleDAO.setDb(db);
	}

    @Override
	public void close() {
        db.close();
	}

    @Override
    public DataAccessorType getType() {
        return DataAccessorType.SQLITE;
    }


    @Override
    public void insertArticle(Article article, String spendId) {
        articleDAO.insert(article, spendId);
    }

    @Override
    public void updateArticle(Article article) {
        articleDAO.update(article);
    }

    @Override
    public void deleteArticle(Article article) {
        articleDAO.delete(article);
    }

    @Override
    public Article selectArticle(String articleId) {
        return null;
    }

    @Override
    public ListArticles selectArticlesOfSpend(String spendId) {
        return null;
    }

    @Override
    public void insertClient(Client client) {
        clientDAO.insert(client);
    }

    @Override
    public void updateClient(Client client) {
        clientDAO.update(client);
    }

    @Override
    public void deleteClient(Client client) {
        clientDAO.delete(client);
    }

    @Override
    public Client selectClient(String clientIdOrEmail) {
        return clientDAO.select(clientIdOrEmail);
    }

    @Override
    public void insertIncome(Income income, String clientId) {
        incomeDAO.insert(income, clientId);
    }

    @Override
    public void updateIncome(Income income) {
        incomeDAO.update(income);
    }

    @Override
    public void deleteIncome(Income income) {
        incomeDAO.delete(income);
    }

    @Override
    public Income selectIncome(String incomeId) {
        return incomeDAO.select(incomeId);
    }

    @Override
    public ListIncomes selectIncomesOfClient(String clientId) {
        return incomeDAO.selectAllOfClient(clientId);
    }

    @Override
    public void insertPayment(Payment payment, String paymentsAccountId) {
        paymentDAO.insert(payment, paymentsAccountId);
    }

    @Override
    public void updatePayment(Payment payment) {
        paymentDAO.update(payment);
    }

    @Override
    public void deletePayment(Payment payment) {
        paymentDAO.delete(payment);
    }

    @Override
    public Payment selectPayment(String paymentId) {
        return paymentDAO.select(paymentId);
    }

    @Override
    public ListPayments selectPaymentsOfPaymentsAccount(String paymentsAccountId) {
        return paymentDAO.selectAllOfPaymentsAccount(paymentsAccountId);
    }

    @Override
    public void insertPaymentsAccount(PaymentsAccount paymentsAccount, String clientId) {
        paymentsAccountDAO.insert(paymentsAccount, clientId);
    }

    @Override
    public void updatePaymentsAccount(PaymentsAccount paymentsAccount) {
        paymentsAccountDAO.update(paymentsAccount);
    }

    @Override
    public void deletePaymentsAccount(PaymentsAccount paymentsAccount) {
        paymentsAccountDAO.delete(paymentsAccount);
    }

    @Override
    public PaymentsAccount selectPaymentsAccount(String paymentsAccountOrClientId) {
        return paymentsAccountDAO.select(paymentsAccountOrClientId);
    }

    @Override
    public void insertPhone(Phone phone, String clientId) {
        phoneDAO.insert(phone, clientId);
    }

    @Override
    public void updatePhone(Phone phone, String clientId) {
        phoneDAO.update(phone, clientId);
    }

    @Override
    public void deletePhone(String clientId) {
        phoneDAO.delete(clientId);
    }

    @Override
    public Phone selectPhone(String clientId) {
        return phoneDAO.select(clientId);
    }

    @Override
    public void insertSpend(Spend spend, String spendsAccountId) {
        spendDAO.insert(spend, spendsAccountId);
    }

    @Override
    public void updateSpend(Spend spend) {
        spendDAO.update(spend);
    }

    @Override
    public void deleteSpend(Spend spend) {
        spendDAO.delete(spend);
    }

    @Override
    public Spend selectSpend(String spendId) {
        return spendDAO.select(spendId);
    }

    @Override
    public ListSpends selectSpendsOfSpendsAccount(String spendsAccountId) {
        return spendDAO.selectAllOfSpendsAccount(spendsAccountId);
    }

    @Override
    public void insertSpendsAccount(SpendsAccount spendsAccount, String clientId) {
        spendsAccountDAO.insert(spendsAccount, clientId);
    }

    @Override
    public void updateSpendsAccount(SpendsAccount spendsAccount) {
        spendsAccountDAO.update(spendsAccount);
    }

    @Override
    public void deleteSpendsAccount(SpendsAccount spendsAccount) {
        spendsAccountDAO.delete(spendsAccount);
    }

    @Override
    public SpendsAccount selectSpendsAccount(String spendsAccountOrClientId) {
        return spendsAccountDAO.select(spendsAccountOrClientId);
    }
}
