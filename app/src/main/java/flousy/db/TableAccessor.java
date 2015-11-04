package flousy.db;

import flousy.util.FlousyCollection;

/**
 * Created by Samir on 11/06/2015.
 */
public interface TableAccessor<T> {

    long insert(T t);

    void update(T t);

    void delete(T t);

    T select(long id);

    FlousyCollection<T> selectAll();
}
