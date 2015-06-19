package flousy.content;

/**
 * Created by Samir on 19/06/2015.
 */
public class Phone {

    private long index;
    private String number;

    public Phone() {}

    public Phone(long index, String number) {
        this.index = index;
        this.number = number;
    }

    public long getIndex() {
        return this.index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "+(" + this.index + ") " + this.number;
    }
}
