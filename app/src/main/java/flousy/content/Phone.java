package flousy.content;

/**
 * Created by Samir on 19/06/2015.
 */
public class Phone {

    private static final String SEPARATOR = "-";
    private long index;
    private String number;

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
        return this.index + SEPARATOR + this.number;
    }

    public static Phone parse(String sPhone) {
        Phone phone = new Phone();

        String[] tab = sPhone.split(SEPARATOR);
        phone.setIndex(Long.parseLong(tab[0]));
        phone.setNumber(tab[1]);

        return phone;
    }
}
