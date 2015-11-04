package flousy.db;

import android.content.Context;

/**
 * Created by Samir on 11/06/2015.
 */
public interface DBAccessor {

    void init(Context context);

    void open();

    void close();

    String getType();
}
