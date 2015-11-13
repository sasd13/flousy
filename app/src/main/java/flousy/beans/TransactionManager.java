package flousy.beans;

import java.util.List;

import flousy.db.DataAccessor;
import flousy.db.DataAccessorFactory;

public class TransactionManager {

    private static DataAccessor dao = DataAccessorFactory.get();
    private static Account account;

    public static void setAccount(Account mAccount) { account = mAccount; }

    public static List<Transaction> getListTransactions() {
        return account.getListTransactions();
    }

    public static void createTransaction(Transaction transaction) {
        dao.insertTransaction(transaction, account);

        getListTransactions().add(transaction);
    }

    public static void updateTransaction(Transaction mTransaction) {
        for (Transaction transaction : getListTransactions()) {
            if (transaction.getId() == mTransaction.getId()) {
                transaction.setTitle(mTransaction.getTitle());
                transaction.setValue(mTransaction.getValue());

                dao.updateTransaction(transaction);

                break;
            }
        }
    }

    public static void removeTransaction(Transaction transaction) {
        dao.deleteTransaction(transaction);

        getListTransactions().remove(transaction);
    }
}
