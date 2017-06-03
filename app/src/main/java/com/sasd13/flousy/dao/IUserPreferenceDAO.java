package com.sasd13.flousy.dao;

import com.sasd13.flousy.bean.user.preference.UserPreference;

import java.util.List;

/**
 * Created by ssaidali2 on 03/06/2017.
 */

public interface IUserPreferenceDAO {

    String TABLE = "userpreferences";
    String COLUMN_ID = "_id";
    String COLUMN_VALUE = "_value";
    String COLUMN_USER = "_user";
    String COLUMN_PREFERENCE = "_preference";

    void update(UserPreference userPreference);

    List<UserPreference> read(String userID);
}
