package flousy.bean.trading;

import java.sql.Timestamp;
import java.util.Observable;

/**
 * Created by Samir on 04/11/2015.
 */
public abstract class TradingAccount implements ITradingAccount {

    private long id;
    private Timestamp dateOpening;
    private double sold;
    private ListTrafficOperations listTrafficOperations;
    private Diary diary;

    protected TradingAccount() {
        this.dateOpening = new Timestamp(System.currentTimeMillis());
        this.sold = 0;
        this.listTrafficOperations = new ListTrafficOperations();
        this.diary = new Diary();

        this.listTrafficOperations.addObserver(this);
    }

    @Override
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Timestamp getDateOpening() {
        return this.dateOpening;
    }

    @Override
    public void setDateOpening(Timestamp dateOpening) {
        this.dateOpening = dateOpening;
    }

    @Override
    public double getSold() {
        return this.sold;
    }

    @Override
    public ListTrafficOperations getListTrafficOperations() {
        return this.listTrafficOperations;
    }

    @Override
    public Diary getDiary() {
        return this.diary;
    }

    @Override
    public void update(Observable observable, Object data) {
        for (ITrafficOperation trafficOperation : this.listTrafficOperations) {
            this.sold += trafficOperation.getValue();
        }
    }

    @Override
    public String toString() {
        return "["
                + "id: " + this.id + ", "
                + "dateopening: " + String.valueOf(this.dateOpening) + ", "
                + "type: " + getTradingAccountType() + ", "
                + "sold: " + this.sold + "]";
    }
}
