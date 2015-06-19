package flousy.session;

import android.content.Context;
import android.content.SharedPreferences;

import proadmin.content.Teacher;
import proadmin.data.dao.DataAccessorManager;
import proadmin.data.dao.accessor.DataAccessor;

/**
 * Created by Samir on 15/03/2015.
 */
public class Session {

    private static final String SESSION_PREFERENCES = "session_preferences";
    private static final String SESSION_ID = "teacher_id";

    private static SharedPreferences preferences;
    private static DataAccessor dao;

    protected Session() {}
    
    public static void start(Context context) {
        preferences = context.getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE);
        dao = DataAccessorManager.getDao();
    }

    public static boolean isLogged() {
        return preferences.contains(SESSION_ID);
    }

    public static String getSessionId() {
        return preferences.getString(SESSION_ID, null);
    }

    public static boolean logIn(String email, String password) {
        dao.open();
        Teacher teacher = dao.selectTeacher(email);
        dao.close();

        try {
            if(teacher.getPassword().compareTo(password) == 0) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(SESSION_ID, teacher.getId().toString());

                return editor.commit();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean logOut() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(SESSION_ID);

        return editor.commit();
    }
}
