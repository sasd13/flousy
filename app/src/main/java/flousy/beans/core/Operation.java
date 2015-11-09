package flousy.beans.core;

import java.sql.Timestamp;

/**
 * Created by Samir on 19/06/2015.
 */
public class Operation {

    private long id;
    private Timestamp date;
    private OperationType type;
    private String name;
    private double value;

    public Operation(OperationType type) {
        this.date = new Timestamp(System.currentTimeMillis());
        this.type = type;
    }

    public Operation(OperationType type, String name, double value) {
        this(type);

        this.name = name;
        this.value = value;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return this.date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public OperationType getType() {
        return this.type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDiaryEntry() {
        return toString();
    }

    public String toString() {
        return "["
                + "id: " + this.id + ", "
                + "date: " + String.valueOf(this.date) + ", "
                + "type: " + String.valueOf(this.type) + ", "
                + "name: " + this.name + ", "
                + "value: " + this.value + "]";
    }
}
