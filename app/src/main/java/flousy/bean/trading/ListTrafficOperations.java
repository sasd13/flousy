package flousy.bean.trading;

import flousy.util.ObservableList;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListTrafficOperations extends ObservableList<ITrafficOperation> {

    @Override
    public ITrafficOperation get(Object id) {
        for (ITrafficOperation trafficOperation : getList()) {
            if (trafficOperation.getId() == (long) id) {
                return trafficOperation;
            }
        }

        return null;
    }
}
