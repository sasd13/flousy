package flousy.bean.traffic;

import flousy.util.ObservableList;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListTraffics extends ObservableList<ITraffic> {

    @Override
    public ITraffic get(Object id) {
        for (ITraffic ITraffic : this.list) {
            if (ITraffic.getId() == (long) id) {
                return ITraffic;
            }
        }

        return null;
    }
}
