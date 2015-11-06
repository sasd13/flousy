package flousy.bean.traffic;

import java.sql.Timestamp;

/**
 * Created by Samir on 05/11/2015.
 */
public interface ITraffic {

    long getId();

    void setId(long id);

    Timestamp getDate();

    void setDate(Timestamp date);

    String getTrafficType();

    String getName();

    void setName(String name);

    String getDiaryEntry();
}
