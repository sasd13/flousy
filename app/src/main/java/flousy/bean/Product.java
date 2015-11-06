package flousy.bean;

/**
 * Created by Samir on 19/06/2015.
 */
public class Product {

    private long id;
    private String name;
    private double price;
    private Category category;

    public Product() {}

    public Product(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

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

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "["
                + "id: " + this.id + ", "
                + "name: " + this.name + ", "
                + "price: " + this.price + ", "
                + "category: " + String.valueOf(this.category) + "]";
    }
}
