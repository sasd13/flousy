package flousy.util;

/**
 * Created by Samir on 19/06/2015.
 */
public interface FlousyCollection<T> extends Iterable<T> {

    T get(long id);

    void add(T t);

    void remove(T t);

    boolean contains(T t);

    int size();
}
