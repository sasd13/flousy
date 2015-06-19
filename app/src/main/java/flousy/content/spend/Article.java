package flousy.content.spend;

/**
 * Created by Samir on 19/06/2015.
 */
public class Article {

    private String id;
    private String name;
    private double price;
    private Category category;

    public Article() {}

    public Article(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
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
}
