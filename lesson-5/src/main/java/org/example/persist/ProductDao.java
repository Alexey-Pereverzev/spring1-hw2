package org.example.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductDao {
    private final EntityManagerFactory emf;

    public ProductDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void initDB() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();    // открытие транзакции
        Product product0 = new Product("Набор пробников для кошек", "1249.90");
        Product product1 = new Product("Печенье для собак вкус яблока", "549.90");
        Product product2 = new Product("Гуляш для собак биг пак", "6099.90");
        Product product3 = new Product("Пробник для кошек New", "315.50");
        Product product4 = new Product("Набор кормов для собак", "1769.90");
        Product product5 = new Product("Жвачка для собак и кошек клюква", "1179.90");
        Product product6 = new Product("Чудо фермент для кошек и собак", "2499.90");
        Product product7 = new Product("Влажный корм для собак и кошек органик картофель", "4899.90");
        Product product8 = new Product("Жевательные добавки для щенков", "2599.90");
        Product product9 = new Product("Жевательные добавки для взрослых собак", "1622.50");
        em.persist(product0);       //  сохранение в БД
        em.persist(product1);
        em.persist(product2);
        em.persist(product3);
        em.persist(product4);
        em.persist(product5);
        em.persist(product6);
        em.persist(product7);
        em.persist(product8);
        em.persist(product9);
        em.getTransaction().commit();
        em.close();
    }




    public Product findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Product product = em.find(Product.class, id);    // поиск по первичному ключу
        em.close();
        return product;
    }

    public List<Product> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Product> products = em.createQuery("from Product", Product.class)           //  запрос на список всех продуктов
                .getResultList();
        em.close();
        return products;
    }

    public void deleteById(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        if (product!=null) em.remove(product);
        em.getTransaction().commit();
        em.close();
    }

    public Product saveOrUpdate(Product product) {
        EntityManager em = emf.createEntityManager();
        List searched = em.createQuery("select id from Product p where p.title = :title")   //  поиск пользователя по имени
                .setParameter("title", product.getTitle()).getResultList();
        System.out.println(searched);

        em.getTransaction().begin();
        if (searched.size()>0) {               //  UPDATE
            Long id = (Long) searched.get(0);
            em.find(Product.class, id).setCost(product.getCost());
        } else {                            //  SAVE
            em.persist(product);
        }
        em.getTransaction().commit();

        em.close();
        return product;
    }

}
