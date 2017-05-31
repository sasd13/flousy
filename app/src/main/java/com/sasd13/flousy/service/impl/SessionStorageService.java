package com.sasd13.flousy.service.impl;

import com.sasd13.androidex.util.SessionStorage;
import com.sasd13.flousy.service.ISessionStorageService;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public class SessionStorageService implements ISessionStorageService {

    private static final String KEY_USERID = "_USERID";
    private static final String KEY_INTERMEDIARY = "_INTERMEDIARY";

    private SessionStorage sessionStorage;

    public SessionStorageService(SessionStorage sessionStorage) {
        this.sessionStorage = sessionStorage;
    }

    @Override
    public String get(String key) {
        return sessionStorage.get(key);
    }

    @Override
    public String getUserID() {
        return get(KEY_USERID);
    }

    @Override
    public String getIntermediary() {
        return get(KEY_INTERMEDIARY);
    }

    @Override
    public void put(String key, String value) {
        sessionStorage.put(key, value);
    }

    @Override
    public void putUserID(String value) {
        put(KEY_USERID, value);
    }

    @Override
    public void putIntermediary(String value) {
        put(KEY_INTERMEDIARY, value);
    }

    @Override
    public void remove(String key) {
        sessionStorage.remove(key);
    }

    @Override
    public void clear() {
        sessionStorage.clear();
    }
}
