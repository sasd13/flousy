package flousy.bean;

import java.sql.Timestamp;

/**
 * Created by Samir on 19/06/2015.
 */
public class Period {

    private static final String SEPARATOR = "/";

    public Timestamp firstDate, lastDate;

    public Period() {
        this.firstDate = this.lastDate = new Timestamp(System.currentTimeMillis());
    }

    public Period(Timestamp firstDate, Timestamp lastDate) {
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }

    @Override
    public String toString() {
        return "["
                + "firstdate: " + String.valueOf(this.firstDate) + ", "
                + "lastdate: " + String.valueOf(this.lastDate) + "]";
    }

    public String toString(boolean combine) {
        if (combine) {
            return String.valueOf(this.firstDate) + SEPARATOR + String.valueOf(this.lastDate);
        } else {
            return toString();
        }
    }

    public static Period parse(String sPeriod) {
        if (sPeriod == null) {
            return null;
        }

        String[] tab = sPeriod.split(SEPARATOR);

        return new Period(Timestamp.valueOf(tab[0]), Timestamp.valueOf(tab[1]));
    }
}
