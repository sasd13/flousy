package flousy.bean;

/**
 * Created by Samir on 19/06/2015.
 */
public class Category {

    private long id;
    private String name;

    public Category() {}

    public Category(String name) { this.name = name; }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "["
                + "id: " + this.id + ", "
                + "name: " + this.name + "]";
    }
}
