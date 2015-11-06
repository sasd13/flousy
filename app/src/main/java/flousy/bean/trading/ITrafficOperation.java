package flousy.bean.trading;

import flousy.bean.operation.IOperation;
import flousy.bean.traffic.ITraffic;

/**
 * Created by Samir on 06/11/2015.
 */
public interface ITrafficOperation extends ITraffic, IOperation {

    String getTrafficOperationType();
}
