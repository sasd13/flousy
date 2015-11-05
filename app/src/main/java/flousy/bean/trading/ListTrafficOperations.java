package flousy.bean.trading;

import flousy.util.ObservableList;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListTrafficOperations extends ObservableList<TrafficOperation> {

    @Override
    public TrafficOperation get(Object id) {
        for (TrafficOperation trafficOperation : this.list) {
            if (trafficOperation.getId() == (long) id) {
                return trafficOperation;
            }
        }

        return null;
    }
}
