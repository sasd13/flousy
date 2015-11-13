package flousy.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private long id;
    private Timestamp dateOpening;
    private String userFirstName, userLastName, userEmail, userPassword;
    private boolean closed;
    private List<Transaction> listTransactions;

    public Account() {
        this.dateOpening = new Timestamp(System.currentTimeMillis());
        this.closed = false;
        this.listTransactions = new ArrayList<>();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDateOpening() {
        return this.dateOpening;
    }

    public void setDateOpening(Timestamp dateOpening) {
        this.dateOpening = dateOpening;
    }

    public String getUserFirstName() {
            return this.userFirstName;
        }

    public void setUserFirstName(String userFirstName) {
            this.userFirstName = userFirstName;
        }

    public String getUserLastName() {
            return this.userLastName;
        }

    public void setUserLastName(String userLastName) {
            this.userLastName = userLastName;
        }

    public String getUserEmail() {
            return this.userEmail;
        }

    public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isClosed() {
        return this.closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void addTransaction(Transaction transaction) {
        this.listTransactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        this.listTransactions.remove(transaction);
    }

    public Transaction getTransaction(long transactionId) {
        for (Transaction transaction : this.listTransactions) {
            if (transaction.getId() == transactionId) {
                return transaction;
            }
        }

        return null;
    }

    public Transaction[] getTransactions() {
        return this.listTransactions.toArray(new Transaction[0]);
    }

    public double getSold() {
        double sold = 0;

        for (Transaction transaction : this.listTransactions) {
            sold += transaction.getValue();
        }

        return sold;
    }

    @Override
    public String toString() {
        return "["
                + "id: " + this.id + ", "
                + "dateopening: " + String.valueOf(this.dateOpening) + ", "
                + "userfirstname: " + this.userLastName + ", "
                + "userlastname: " + this.userLastName + ", "
                + "useremail: " + this.userEmail + ", "
                + "userpassword: " + this.userPassword + "]";
    }
}
