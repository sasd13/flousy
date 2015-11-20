package flousy.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private long id;
    private Timestamp dateOpening;
    private boolean closed;
    private Customer customer;
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

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);

        transaction.setAccount(this);
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
                + "dateopening: " + String.valueOf(this.dateOpening) + "]";
    }
}
