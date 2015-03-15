package flousy.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Samir on 07/03/2015.
 */
public class UserManager {

    public static final String TYPE_DATA = "Data";
    public static final String TYPE_SESSION = "Session";

    private Context context;

    public UserManager(Context context) {
        this.context = context;
    }

    protected SharedPreferences getSettings(String type) {
        return this.context.getSharedPreferences(type, Context.MODE_PRIVATE);
    }

    public UserManager getManager(String type) {
        switch (type) {
            case TYPE_DATA :
                return new DataManager(this.context);
            case TYPE_SESSION :
                return new SessionManager(this.context);
        }

        return null;
    }
}
