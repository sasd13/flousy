package flousy.bean.user;

import java.sql.Timestamp;
import java.util.Observer;

/**
 * Created by Samir on 05/11/2015.
 */
public interface IAccount {

    long getId();

    void setId(long id);

    Timestamp getDateOpening();

    void setDateOpening(Timestamp dateOpening);
}
