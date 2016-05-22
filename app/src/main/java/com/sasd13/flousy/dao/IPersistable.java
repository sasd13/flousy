package com.sasd13.flousy.dao;

/**
 * Created by Samir on 27/02/2016.
 */
public interface IPersistable<T> {

    void persist(T t);
}
