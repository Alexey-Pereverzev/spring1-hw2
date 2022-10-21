package org.example.persist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "products_customers",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;

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
        this.customers = new ArrayList<>();
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
