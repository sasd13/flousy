package flousy.bean;

/**
 * Created by Samir on 19/06/2015.
 */
public class Phone {

    private static final String SEPARATOR = "-";

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
        return "["
                + "index: " + this.index + ", "
                + "number: " + this.number + "]";
    }

    public String toString(boolean combine) {
        if (combine) {
            return this.index + SEPARATOR + this.number;
        } else {
            return toString();
        }
    }

    public static Phone parse(String sPhone) {
        if (sPhone == null) {
            return null;
        }

        Phone phone = new Phone();

        String[] tab = sPhone.split(SEPARATOR);
        phone.setIndex(Long.parseLong(tab[0]));
        phone.setNumber(tab[1]);

        return phone;
    }
}
