package flousy.bean.user;

import flousy.util.List;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListUsers extends List<User> {

    @Override
    public User get(Object id) {
        for (User user : this.list) {
            if (user.getId() == (long) id) {
                return user;
            }
        }

        return null;
    }
}
