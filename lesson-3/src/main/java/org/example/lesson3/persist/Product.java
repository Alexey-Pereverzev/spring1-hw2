package org.example.lesson3.persist;

public class Product {
    private long id;
    private String title;
    private String cost;


    public Product(int id, String title, String cost) {
        cost.replace(',','.');
        double d = 0;
        try {
            d = Double.parseDouble(cost);
            if (d<0) {
                d=0;
            }
        } catch (NumberFormatException ignored) {
        }
        this.cost = String.format("%.2f", d).replace(',','.');
        this.id = id;
        this.title = title;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        cost.replace(',','.');
        double d = 0;
        try {
            d = Double.parseDouble(cost);
            if (d<0) {
                d=0;
            }
        } catch (NumberFormatException ignored) {
        }
        this.cost = String.format("%.2f", d).replace(',','.');
    }
}
