package flousy.content;

import java.sql.Timestamp;

/**
 * Created by Samir on 19/06/2015.
 */
public class Period {

    private static final String SEPARATOR = "/";
    public Timestamp firstDate;
    public Timestamp lastDate;

    public Period() {
        this.firstDate = this.lastDate = new Timestamp(System.currentTimeMillis());
    }

    public Period(Timestamp firstDate, Timestamp lastDate) {
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }

    public Timestamp getFirstDate() {
        return this.firstDate;
    }

    public void setFirstDate(Timestamp firstDate) {
        this.firstDate = firstDate;
    }

    public Timestamp getLastDate() {
        return this.lastDate;
    }

    public void setLastDate(Timestamp lastDate) {
        this.lastDate = lastDate;
    }

    @Override
    public String toString() {
        return this.firstDate + SEPARATOR + this.lastDate;
    }

    public static Period parse(String sPeriod) {
        String[] tab = sPeriod.split(SEPARATOR);

        return new Period(Timestamp.valueOf(tab[0]), Timestamp.valueOf(tab[1]));
    }
}
