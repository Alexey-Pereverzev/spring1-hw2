package org.example.lesson3.persist;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductMatrix {
    private final Map<Long, Product> productMap = new ConcurrentHashMap<>(10);
    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init(){
        this.insert(new Product(0, "Набор пробников для кошек", "1249.90"));
        this.insert(new Product(0, "Печенье для собак вкус яблока", "549.90"));
        this.insert(new Product(0, "Гуляш для собак биг пак", "6099.90"));
        this.insert(new Product(0, "Пробник для кошек New", "315.50"));
        this.insert(new Product(0, "Набор кормов для собак", "1769.90"));
        this.insert(new Product(0, "Жвачка для собак и кошек клюква", "1179.90"));
        this.insert(new Product(0, "Чудо фермент для кошек и собак", "2499.90"));
        this.insert(new Product(0, "Влажный корм для собак и кошек органик картофель", "4899.90"));
        this.insert(new Product(0, "Жевательные добавки для щенков", "2599.90"));
        this.insert(new Product(0, "Жевательные добавки для взрослых собак", "1622.50"));
    }

    public ArrayList<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Product findById (long id) {
        return productMap.get(id);
    }

    public void insert(Product product) {
        long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(id,product);
    }

    public void update(Product product) {
        productMap.put(product.getId(), product);
    }

    public void delete(long id) {
        productMap.remove(id);
    }
}
