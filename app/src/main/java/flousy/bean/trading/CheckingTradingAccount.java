package flousy.bean.trading;

import java.sql.Timestamp;
import java.util.Observable;

import flousy.util.Diary;

/**
 * Created by Samir on 04/11/2015.
 */
public class CheckingTradingAccount implements ITradingAccount {

    private long id;
    private Timestamp dateOpening;
    private double sold;
    private ListTraffics listTraffics;
    private Diary diary;

    public CheckingTradingAccount() {
        this.dateOpening = new Timestamp(System.currentTimeMillis());
        this.sold = 0;
        this.listTraffics = new ListTraffics();
        this.diary = new Diary();

        this.listTraffics.addObserver(this);
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
    public String getTradingAccountType() {
        return "CHECKING";
    }

    @Override
    public double getSold() {
        return this.sold;
    }

    @Override
    public ListTraffics getListTraffics() {
        return this.listTraffics;
    }

    @Override
    public Diary getDiary() {
        return this.diary;
    }

    @Override
    public void update(Observable observable, Object data) {
        for (ITraffic ITraffic : this.listTraffics) {
            if (ITraffic.getTrafficType() == "OPERATION") {
                this.sold += ((TrafficOperation) ITraffic).getValue();
            }
        }
    }

    @Override
    public String toString() {
        return "["
                + "id: " + this.id + ", "
                + "dateopening: " + this.dateOpening.toString() + ", "
                + "type: " + getTradingAccountType() + ", "
                + "sold: " + this.sold + "]";
    }
}
