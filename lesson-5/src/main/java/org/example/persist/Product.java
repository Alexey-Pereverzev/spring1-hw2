package org.example.persist;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 128, unique = true, nullable = false)
    private String title;

    @Column(length = 16, nullable = false)
    private String cost;


    public Product(String title, String cost) {
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
        this.title = title;
    }

    public Product() {
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
                d = 0;
            }
        } catch (NumberFormatException ignored) {
        }
        this.cost = String.format("%.2f", d).replace(',','.');
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
