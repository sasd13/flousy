package flousy.bean.trading;

import java.sql.Timestamp;

import flousy.bean.operation.IOperation;

/**
 * Created by Samir on 19/06/2015.
 */
public class TrafficOperation implements IOperation, ITraffic {

    private long id;
    private Timestamp date;
    private String name;
    private IOperation operation;

    public TrafficOperation() {
        this.date = new Timestamp(System.currentTimeMillis());
    }

    public TrafficOperation(IOperation operation) {
        this();

        this.operation = operation;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Timestamp getDate() {
        return this.date;
    }

    @Override
    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String getTrafficType() {
        return "OPERATION";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDiaryEntry() {
        return "["
                + "id: " + this.id + ", "
                + "date: " + this.date.toString() + ", "
                + "traffic: " + getTrafficType() + ", "
                + "name: " + this.name + ", "
                + "operation: " + this.operation.toString() + "]";
    }

    @Override
    public String getOperationType() {
        return this.operation.getOperationType();
    }

    @Override
    public double getValue() {
        return this.operation.getValue();
    }

    @Override
    public void setValue(double value) {
        this.operation.setValue(value);
    }

    public IOperation getOperation() {
        return this.operation;
    }

    public void setOperation(IOperation operation) {
        this.operation = operation;
    }
}
