package flousy.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private long id;
    private Timestamp dateOpening;
    private String userFirstName, userLastName, userEmail, userPassword;
    private List<Transaction> transactions;

    public Account() {
        this.dateOpening = new Timestamp(System.currentTimeMillis());
        this.transactions = new ArrayList<>();
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

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
    }

    public Transaction[] getTransactions() {
        return this.transactions.toArray(new Transaction[this.transactions.size()]);
    }

    public double getSold() {
        double sold = 0;

        for (Transaction transaction : this.transactions) {
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