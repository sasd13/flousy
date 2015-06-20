package flousy.data.dao.accessor;

import flousy.content.Phone;

/**
 * Created by Samir on 11/06/2015.
 */
public interface PhoneAccessor {

    void insertPhone(Phone phone, String clientId);

    void updatePhone(Phone phone, String clientId);

    void deletePhone(String clientId);

    Phone selectPhone(String clientId);
}
