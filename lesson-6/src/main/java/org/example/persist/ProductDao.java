package org.example.persist;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class ProductDao {

    private final EntityManagerEngine engine;

    public ProductDao(EntityManagerFactory emf) {
        engine = new EntityManagerEngine(emf);
    }


    public Product findById(Long id) {
        return engine.executeInEM(em -> em.find(Product.class, id));   // поиск по первичному ключу
    }

    public List<Product> findAll() {
        return engine.executeInEM(em -> em.createQuery("from Product", Product.class).getResultList());
        //  запрос на список всех продуктов
    }

    public void deleteById(Long id) {
        engine.executeInTransaction(em -> {
            Product product = em.find(Product.class, id);
            if (product!=null) em.remove(product);
        });
    }

    public Product saveOrUpdate(Product product) {
        engine.executeInTransaction(em -> {
            List searched = em.createQuery("select id from Product p where p.title = :title")   //  поиск продукта по имени
                    .setParameter("title", product.getTitle()).getResultList();
            if (searched.size()>0) {               //  UPDATE
                Long id = (Long) searched.get(0);
                em.find(Product.class, id).setCost(product.getCost());
            } else {                            //  SAVE
                em.persist(product);
            }
        });
        return product;
    }
}
