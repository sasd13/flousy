package flousy.bean.operation;

/**
 * Created by Samir on 19/06/2015.
 */
public abstract class Operation implements IOperation {

    private double value;

    protected Operation() {
        this.value = 0;
    }

    protected Operation(double value) { this.value = value; }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "["
                + "type: " + getOperationType()
                + "value: " + this.value + "]";
    }
}
