package flousy.content.finance;

import java.util.Date;

/**
 * Created by Samir on 19/06/2015.
 */
public class Payment {

    private String id;
    private double value;
    private Date date;

    public Payment() {}

    public Payment(String id, double value, Date date) {
        this.id = id;
        this.value = value;
        this.date = date;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
