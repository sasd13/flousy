package flousy.content.finance;

import java.sql.Timestamp;

/**
 * Created by Samir on 19/06/2015.
 */
public class Payment {

    private long id;
    private Timestamp date;
    private double value;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return this.date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
