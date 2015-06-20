package flousy.content;

import java.util.Date;

/**
 * Created by Samir on 19/06/2015.
 */
public class Period {

    public Date firstDate;
    public Date lastDate;

    public Period() {
        this.lastDate = new Date(System.currentTimeMillis());
    }

    public Period(Date firstDate, Date lastDate) {
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }
}
