package flousy.content;

/**
 * Created by Samir on 19/06/2015.
 */
public abstract class Account {

    protected String id;

    protected Account() {}

    protected Account(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
