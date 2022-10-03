package org.example.lesson2.matrix;

public class Product {          //  Product
    private long id;
    private final String title;
    private final double cost;

    public Product(long id, String title, double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }

}
