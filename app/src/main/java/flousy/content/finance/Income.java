package flousy.content.finance;

import java.util.Date;

import flousy.content.id.IncomeId;

/**
 * Created by Samir on 19/06/2015.
 */
public class Income {

    private String id;
    private double value;
    private Date startDate;
    private Date endDate;
    private Periodicity periodicity;

    public Income() {}

    public Income(double value, Date startDate, Date endDate, Periodicity periodicity) {
        this.value = value;
        this.startDate = startDate;
        this.endDate = endDate;
        this.periodicity = periodicity;
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

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Periodicity getPeriodicity() {
        return this.periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }
}
