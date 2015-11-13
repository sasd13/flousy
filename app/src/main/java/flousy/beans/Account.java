package flousy.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private long id;
    private Timestamp dateOpening;
    private List<Transaction> listTransactions;

    public Account() {
        this.dateOpening = new Timestamp(System.currentTimeMillis());
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

    public List<Transaction> getListTransactions() {
        return this.listTransactions;
    }

    public long getSold() {
        long sold = 0;

        for (Transaction transaction : this.listTransactions) {
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
