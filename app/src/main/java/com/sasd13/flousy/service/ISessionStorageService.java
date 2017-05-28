package com.sasd13.flousy.service;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public interface ISessionStorageService {

    String get(String key);

    String getUserID();

    String getIntermediary();

    void put(String key, String value);

    void putUserID(String value);

    void putIntermediary(String value);

    void clear();
}
