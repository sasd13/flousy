package flousy.data.dao.accessor;

import flousy.content.Client;

/**
 * Created by Samir on 11/06/2015.
 */
public interface ClientAccessor {

    void insertClient(Client client);

    void updateClient(Client client);

    void deleteClient(Client client);

    Client selectClient(String clientIdOrEmail);
}
