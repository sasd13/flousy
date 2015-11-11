package flousy.beans.core;

import java.sql.Timestamp;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Samir on 04/11/2015.
 */
public class Account implements Observer {

    private long id;
    private Timestamp dateOpening;
    private double sold;
    private ListOperations listOperations;

    public Account() {
        this.dateOpening = new Timestamp(System.currentTimeMillis());
        this.sold = 0;

        setListOperations(new ListOperations());
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

    public double getSold() {
        return this.sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    public ListOperations getListOperations() {
        return this.listOperations;
    }

    public void setListOperations(ListOperations listOperations) {
        this.listOperations = listOperations;
        this.listOperations.addObserver(this);
    }

    public void update(Observable observable, Object data) {
        update();
    }

    public void update() {
        this.sold = 0;

        for (Operation operation : this.listOperations) {
            this.sold += operation.getValue();
        }
    }

    @Override
    public String toString() {
        return "["
                + "id: " + this.id + ", "
                + "dateopening: " + String.valueOf(this.dateOpening) + ", "
                + "sold: " + this.sold + "]";
    }
}
