package org.example.lesson2.matrix;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductMatrix {
    private final Map<Long, Product> productMap = new ConcurrentHashMap<>(10);
    private final AtomicLong identity = new AtomicLong(0);

    public ArrayList<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public void insert(Product product) {
        long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(id,product);
    }

}
