package com.sasd13.flousy.dao;

import java.util.List;

/**
 * Created by ssaidali2 on 03/06/2017.
 */

public interface IPreferenceDAO {

    String TABLE = "preferences";
    String COLUMN_ID = "_id";
    String COLUMN_CATEGORY = "_category";
    String COLUMN_NAME = "_name";
    String COLUMN_DEFAULTVALUE = "_defaultvalue";

    List<Preference> readAll();
}
