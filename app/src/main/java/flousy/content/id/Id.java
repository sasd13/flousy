package flousy.content.id;

/**
 * Created by Samir on 10/06/2015.
 */
public abstract class Id {

    protected String value;

    protected Id() {}

    public Id(String value) {
        this.value = value;
    }

    public abstract String getKey();

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Id) {
            if (o.toString().compareTo(this.value) == 0) {
                return true;
            }
        }

        return false;
    }
}
